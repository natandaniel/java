package jpa.examples.online_learning_platform.course.v3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseIntegrationTest {

  private EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;

  @BeforeAll
  void setupEntityManagerFactory() {
    entityManagerFactory = Persistence.createEntityManagerFactory("test-persistence-unit-v3");
  }

  @BeforeEach
  void setupEntityManager() {
    entityManager = entityManagerFactory.createEntityManager();
  }

  @AfterEach
  void tearDownEntityManager() {
    if (entityManager != null) {
      entityManager.close();
    }
  }

  @AfterAll
  void tearDownEntityManagerFactory() {
    if (entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }

  @Test
  void testCreateAndRetrieveCourseWithModulesAndLessonUrls() {
    EntityTransaction transaction = entityManager.getTransaction();

    // Start transaction
    transaction.begin();

    // Create a new course
    Course course = new Course();
    course.setTitle("Learning Java");
    course.setDescription("A comprehensive course on Java programming");
    course.setImageUrl("https://example.com/java-course-image.png");
    course.setPrice(99.99f);

    // Create a course module
    CourseModule module = new CourseModule(course, 1, "Introduction to Java",
        "This module covers the basics of Java programming.",
        "https://example.com/module-intro-image.png");

    // Create a lesson with a content URL
    Lesson lesson = new Lesson(null, 1, "Getting Started with Java",
        "This lesson introduces the basics of Java, including setting up the development " +
            "environment.",
        module, "https://storage.example.com/java-lesson1.html");

    // Establish relationships
    module.addLesson(lesson);
    course.addModule(module);

    // Persist data
    entityManager.persist(course);

    // Commit transaction
    transaction.commit();

    // Clear persistence context to ensure retrieval from database
    entityManager.clear();

    // Retrieve course
    Course retrievedCourse = entityManager.find(Course.class, course.getId());

    // Assertions
    assertNotNull(retrievedCourse, "Course should not be null");
    assertEquals("Learning Java", retrievedCourse.getTitle(), "Course title should match");
    assertEquals(1, retrievedCourse.getModules().size(), "Course should have one module");

    CourseModule retrievedModule = retrievedCourse.getModules().get(0);
    assertEquals("Introduction to Java", retrievedModule.getTitle(), "Module title should match");
    assertEquals(1, retrievedModule.getLessons().size(), "Module should have one lesson");

    Lesson retrievedLesson = retrievedModule.getLessons().get(0);
    assertEquals("Getting Started with Java", retrievedLesson.getTitle(),
        "Lesson title should match");
    assertEquals("https://storage.example.com/java-lesson1.html", retrievedLesson.getContentUrl(),
        "Lesson content URL should match");
  }
}