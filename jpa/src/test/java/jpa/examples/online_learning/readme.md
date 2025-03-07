# Guide to Integration Testing with `CourseIntegrationTest` in JPA

This document explains how to set up, manage, and clean up `EntityManager` and the database for efficient integration
testing, ensuring data isolation and proper cleanup for the entire test suite.

## Persistence Configuration (`persistence.xml`)

The integration tests utilize the `test-persistence-unit` persistence unit to configure an in-memory H2 database. This
database is only initialized at the beginning of the test suite, and tables are cleaned up at the end. Thus, all tests
run against the same schema, but the database starts from a fresh state for all tests.

### Key Features:

1. **Database Initialization:** Tables are created and deleted only **once** during the setup (`@BeforeAll`) and
   teardown (`@AfterAll`) phases.
2. **In-Memory Database:** A clean H2 database (`jdbc:h2:mem`) is used to simulate persistence during testing.
3. **Reverse-Order Cleanup:** Tables are explicitly cleared at the end to ensure proper handling of referential
   integrity.

## EntityManager Lifecycle Management

The `EntityManager` and database schema are initialized and cleaned as follows:

### 1. **Set Up the Database and Create Tables Once**

The database schema is initialized at the start of the test suite in the `@BeforeAll` phase. The `EntityManagerFactory`
is created to manage the persistence context, and all tables are created via Hibernate:

```java

@BeforeAll
static void setupEntityManagerFactory() {
  entityManagerFactory = Persistence.createEntityManagerFactory("test-persistence-unit");
}
```

### 2. **Create an `EntityManager` for Each Test**

Before each test, a new `EntityManager` instance is created, and a transaction is immediately started. This ensures all
database operations performed in one test will not leak into other tests:

```java

@BeforeEach
void setupEntityManager() {
  entityManager = entityManagerFactory.createEntityManager();
  entityManager.getTransaction().begin();
}
```

### 3. **Close and Commit/Rollback After Each Test**

For each test, once the operations are complete:

- If a transaction is still active, it is rolled back.
- The `EntityManager` instance is closed to ensure no resources are leaked between tests.

```java

@AfterEach
void tearDownEntityManager() {
  if (entityManager.getTransaction().isActive()) {
    entityManager.getTransaction().rollback();
  }
  entityManager.close();
}
```

### 4. **Clean Up the Database Only After the Entire Test Suite**

Once all tests are completed, tables are explicitly **cleared and deleted** in reverse dependency order at the end of
the test suite during the `@AfterAll` phase. The `EntityManagerFactory` is also shut down at this stage.

```java

@AfterAll
static void tearDownEntityManagerFactory() {
  if (entityManager.getTransaction().isActive()) {
    entityManager.getTransaction().rollback();
  }

  // Explicit cleanup of tables in reverse dependency order
  entityManager.getTransaction().begin();
  entityManager.createQuery("DELETE FROM lesson_figures").executeUpdate();
  entityManager.createQuery("DELETE FROM lesson_texts").executeUpdate();
  entityManager.createQuery("DELETE FROM lesson_code_samples").executeUpdate();
  entityManager.createQuery("DELETE FROM lesson_sections").executeUpdate();
  entityManager.createQuery("DELETE FROM lessons").executeUpdate();
  entityManager.createQuery("DELETE FROM course_modules").executeUpdate();
  entityManager.createQuery("DELETE FROM courses").executeUpdate();
  entityManager.getTransaction().commit();

  if (entityManagerFactory != null) {
    entityManagerFactory.close();
  }
}
```

This ensures that:

- The database is initialized at the start and cleaned only once at the end.
- Deleting tables manually prevents cascading or referential integrity issues in the case of complex relationships.