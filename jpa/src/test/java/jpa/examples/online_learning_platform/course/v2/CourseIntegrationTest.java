package jpa.examples.online_learning_platform.course.v2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseIntegrationTest {
  private static EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;

  @BeforeAll
  static void setupEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory("test-persistence-unit-v2");
  }

  @AfterEach
  void tearDownEntityManager() {
    if (entityManager.getTransaction().isActive()) {
      entityManager.getTransaction().rollback();
    }
    entityManager.getTransaction().begin();
    entityManager.createQuery("DELETE FROM lessons").executeUpdate();
    entityManager.createQuery("DELETE FROM course_modules").executeUpdate();
    entityManager.createQuery("DELETE FROM courses").executeUpdate();
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  @BeforeEach
  void setupEntityManager() {
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
  }

  @AfterAll
  static void tearDownEntityManagerFactory() {
    if (entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }

  @Test
  void testCreateAndPersistCourseWithModulesAndLessons() {
    EntityTransaction transaction = entityManager.getTransaction();
    try {
      // Create and persist a Course
      Course course = new Course();
      course.setTitle("Java Programming");
      course.setDescription("Learn the fundamentals of Java programming.");
      course.setImageUrl("https://example.com/java-image.jpg");
      course.setPrice(49.99f);

      // Create and persist a CourseModule
      CourseModule module =
          new CourseModule(course, 1, "Introduction to Java", "Basic Java concepts",
              "https://example.com/module-image.jpg");
      course.addModule(module);

      // Create and persist a Lesson with JSON content
      String lessonContentJson = """
                                 {
                                     "sections": [
                                         {
                                             "title": "What is Java?",
                                             "content": "Java is a high-level programming language."
                                         },
                                         {
                                             "title": "Setting up your environment",
                                             "content": "Install the JDK and set up your IDE."
                                         }
                                     ]
                                 }
                                 """;

      Lesson lesson =
          new Lesson(null, 1, "Getting Started with Java", "Overview of Java programming", module,
              lessonContentJson);
      module.addLesson(lesson);

      entityManager.persist(course);

      transaction.commit();
    }
    catch (Exception e) {
      transaction.rollback();
      fail("Transaction failed: " + e.getMessage());
    }

    // Retrieve and validate the Course
    Course fetchedCourse =
        entityManager.createQuery("SELECT c FROM courses c WHERE c.title = :title", Course.class)
                     .setParameter("title", "Java Programming")
                     .getSingleResult();

    assertNotNull(fetchedCourse);
    assertEquals("Java Programming", fetchedCourse.getTitle());
    assertEquals(1, fetchedCourse.getModules().size());

    CourseModule fetchedModule = fetchedCourse.getModules().get(0);
    assertEquals("Introduction to Java", fetchedModule.getTitle());
    assertEquals(1, fetchedModule.getLessons().size());

    Lesson fetchedLesson = fetchedModule.getLessons().get(0);
    assertNotNull(fetchedLesson.getContent());
    assertTrue(fetchedLesson.getContent().contains("What is Java?"));
  }
}