# Managing Entities

Entities are managed by **EntityManager** instances, which define a persistence context—representing a set of managed entities within a specific data store. The **EntityManager** interface provides methods to interact with this context.

---

## 1. EntityManager Interface

The **EntityManager** API manages entity instances by enabling operations like creation, removal, lookup by primary keys, and running queries.

### 1.1 Container-Managed Entity Managers

- Persistence context is **automatically propagated** across components in a single JTA (Java Transaction API) transaction.
- No need to explicitly pass `EntityManager` instances between components.
- **Lifecycle management** is handled by the Java EE container.
- EntityManager injection via `@PersistenceContext` annotation:

  ```plaintext
  @PersistenceContext
  EntityManager em;
  ```

---

### 1.2 Application-Managed Entity Managers

- **Persistence context is not propagated** across components.
- `EntityManager` lifecycle is managed manually by the application.
- Each `EntityManager` instance creates an **isolated persistence context**.
- Used when:
    - Persistence context should not follow JTA transaction boundaries.
    - EntityManager injection is not possible due to its non-thread-safe nature (use `EntityManagerFactory` instead).
- **Creation Steps**:
    1. Inject an `EntityManagerFactory` using `@PersistenceUnit`.
    2. Obtain an `EntityManager` instance via `emf.createEntityManager()`.

- **Transactions**: Use `javax.transaction.UserTransaction` to manage JTA transactions explicitly.

---

### 1.3 Finding Entities by Primary Key

- Look up entities using the `find` method of the EntityManager:
  ```plaintext
  em.find(EntityClass.class, primaryKey);
  ```

---

### 1.4 Managing Entity Lifecycle

Entity instances can exist in one of four states:
1. **New**: No persistent identity, not associated with any persistence context.
2. **Managed**: Associated with a persistence context; changes are tracked/updated.
3. **Detached**: Persistent identity exists, but not actively associated with a persistence context.
4. **Removed**: Scheduled for deletion from the data store.

---

## 2. Persisting Entity Instances

- Use the `persist` method to make new entities persistent or propagate persistence via cascades (`cascade = PERSIST` or `cascade = ALL`).
- **Managed vs. Non-Managed Scenarios**:
    - New entities become managed after `persist()` is called.
    - Persistent entities skip the operation but propagate cascades.
    - Removed entities become managed again after `persist()` is invoked.
    - Detached entities may throw `IllegalArgumentException` or cause transaction failures on commit.

---

## 3. Removing Entity Instances

- Use the `remove` method to schedule managed entities or related entities (with cascade `REMOVE` or `ALL`) for deletion.
- **Special Cases**:
    - Detached entities: May throw `IllegalArgumentException` or cause transaction commit failures.
    - Newly created entities: `remove` is ignored.
    - Already removed entities: `remove` is ignored.

---

## 4. Synchronizing Data to the Database

- Data synchronization happens when a transaction commits.
- **Manual Synchronization**:
    - Call `flush()` to force synchronization.
    - Changes cascade based on relationship annotations (e.g., cascade `ALL` or `PERSIST`).
    - For removed entities, `flush()` deletes the data immediately.

---

## 5. Persistence Units

A **persistence unit** groups all entity classes managed by **EntityManager** instances within an application. Its definition resides in the `persistence.xml` file.

### Key Elements of persistence.xml:
- **persistence-unit name**: Unique identifier for the persistence unit.
- **jta-data-source**: Specifies the JTA-aware data source (e.g., `jdbc/MyOrderDB`).
- **class**: Lists explicitly defined managed persistence classes.
- **jar-file**: Identifies JAR files containing persistence classes.

---

### Packaging Guidelines for Persistence Units:
- **WAR file**:
    - Place `persistence.xml` in `WEB-INF/classes/META-INF`.
- **EJB JAR file**:
    - Place `persistence.xml` in `META-INF`.
- **JAR file for WAR/EAR inclusion**:
    - For WAR: Place in `WEB-INF/lib`.
    - For EAR: Place in the library directory.

> **Note**: Placing JARs at the root of EAR files as persistence unit roots is no longer supported (Java Persistence API 1.0 limitation).

---