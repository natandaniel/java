package jpa.examples.online_learning_platform.course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseIntegrationTest {
  private static EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;

  @BeforeAll
  static void setupEntityManagerFactory() {
    entityManagerFactory =
        Persistence.createEntityManagerFactory("testPersistenceUnit"); // Name from persistence.xml
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
  static void tearDownEntityManagerFactory() {
    if (entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }

  @Test
  void testCoursePersistence() {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    Course course = createCourse(null, 1, "Java Programming 101", "Learn Java basics.",
        "https://example.com/java.png");

    CourseModule module1 = createCourseModule(null, course, "Java Programming Language",
        "Introduction to the Java programming language", "https://example.com/module1.png");
    course.addModule(module1);

    Lesson lesson1 = createLesson(null, module1, 1, "Lesson 1", "Learn about the basics of Java.");
    module1.addLesson(lesson1);

    LessonSection section1 = new LessonSection(null, "Getting Started", 1, null, lesson1);
    lesson1.addSection(section1);

    LessonText text =
        new LessonText(null, 1, section1, "Java is an object-oriented programming language.");
    section1.addLessonContent(text);

    LessonFigure figure =
        new LessonFigure(null, 2, section1, "alt", "figure", "https://example.com/figure.png");
    section1.addLessonContent(figure);

    LessonCode code = new LessonCode(null, 3, section1, "java",
        "public class HelloWorld {\nSystem.out.println(\"Hello World!\");\n}");
    section1.addLessonContent(code);

    LessonSection subsection = new LessonSection(null, "Downloading an IDE", 1, section1, lesson1);
    LessonText text2 = new LessonText(null, 1, subsection,
        "An IDE is a software tool that can be used to develop Java code.");
    subsection.addLessonContent(text2);
    section1.addSubsection(subsection);

    entityManager.persist(course);
    transaction.commit();

    // Clear the persistence context to simulate a fresh fetch
    entityManager.clear();

    // Fetch Course from the database
    Course fetchedCourse = entityManager.find(Course.class, 1);

    // Assertions
    assertNotNull(fetchedCourse);
    assertEquals("Java Programming 101", fetchedCourse.getTitle());
    assertEquals("Learn Java basics.", fetchedCourse.getDescription());
    assertEquals(1, fetchedCourse.getModules().size());
  }

  @Test
  void testFindAllCourses() {
    // Populate some example data
    entityManager.getTransaction().begin();
    Course course1 = new Course();
    course1.setTitle("Course 1");
    course1.setDescription("Description 1");
    entityManager.persist(course1);

    Course course2 = new Course();
    course2.setTitle("Course 2");
    course2.setDescription("Description 2");
    entityManager.persist(course2);
    entityManager.getTransaction().commit();

    // Query all courses
    List<Course> courses =
        entityManager.createQuery("SELECT c FROM courses c", Course.class).getResultList();

    // Assertions
    assertFalse(courses.isEmpty());
    assertEquals(2, courses.size());
  }

  private static Course createCourse(Integer id, int order, String title, String description,
      String imageUrl) {
    Course course = new Course();
    course.setId(id);
    course.setIndex(order);
    course.setTitle(title);
    course.setDescription(description);
    course.setImageUrl(imageUrl);
    return course;
  }

  private static CourseModule createCourseModule(Integer id, Course course, String title,
      String description, String imageUrl) {
    CourseModule module = new CourseModule();
    module.setCourse(course);
    module.setId(id);
    module.setTitle(title);
    module.setDescription(description);
    module.setImageUrl(imageUrl);
    return module;
  }

  private static Lesson createLesson(Integer id, CourseModule module, int order, String title,
      String description) {
    Lesson lesson = new Lesson();
    lesson.setId(id);
    lesson.setModule(module);
    lesson.setIndex(order);
    lesson.setTitle(title);
    lesson.setDescription(description);
    return lesson;
  }
}