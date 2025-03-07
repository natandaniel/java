package jpa.examples.online_learning.v1;

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
    entityManagerFactory = Persistence.createEntityManagerFactory("test-persistence-unit-v1");
  }

  @AfterEach
  void tearDownEntityManager() {
    if (entityManager.getTransaction().isActive()) {
      entityManager.getTransaction().rollback();
    }
    entityManager.getTransaction().begin();
    entityManager.createQuery("DELETE FROM lesson_figures").executeUpdate();
    entityManager.createQuery("DELETE FROM lesson_texts").executeUpdate();
    entityManager.createQuery("DELETE FROM lesson_code_samples").executeUpdate();
    entityManager.createQuery("DELETE FROM lesson_sections").executeUpdate();
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

  // Test for parent-child relationship in LessonSection
  @Test
  void testParentChildSections() {
    EntityTransaction transaction = entityManager.getTransaction();

    // Create Course, Module, Lesson, and hierarchical LessonSections
    Course course =
        createCourse("Cybersecurity", "Comprehensive Course on Cybersecurity", "cyber.jpg");
    CourseModule module =
        createCourseModule(course, "Network Security", "Understanding firewalls and protocols",
            "network.jpg");
    Lesson lesson = createLesson(module, "Understanding Firewalls", "A deep dive into firewalls");

    // Create main section and subsections
    LessonSection parentSection = createLessonSection(lesson, "Firewall Basics");
    LessonSection childSection1 =
        createLessonChildSection(lesson, parentSection, 1, "Packet " + "Filtering");
    LessonSection childSection2 =
        createLessonChildSection(lesson, parentSection, 2, "Stateful " + "Inspection");

    entityManager.persist(course); // We only persist the Course, and everything cascades
    transaction.commit();

    // Clear persistence context to ensure we are fetching fresh data from the database
    entityManager.clear();

    // Fetch the course and verify the entire hierarchy
    Course persistedCourse =
        entityManager.createQuery("SELECT c FROM courses c WHERE c.title = :title", Course.class)
                     .setParameter("title", "Cybersecurity")
                     .getSingleResult();
    assertNotNull(persistedCourse);
    assertEquals("Cybersecurity", persistedCourse.getTitle());
    assertEquals("Comprehensive Course on Cybersecurity", persistedCourse.getDescription());
    assertEquals(1, persistedCourse.getModules().size());

    // Drill down to module
    CourseModule persistedModule = persistedCourse.getModules().get(0);
    assertNotNull(persistedModule);
    assertEquals("Network Security", persistedModule.getTitle());
    assertEquals("Understanding firewalls and protocols", persistedModule.getDescription());
    assertEquals(1, persistedModule.getLessons().size());

    // Drill down to lesson
    Lesson persistedLesson = persistedModule.getLessons().get(0);
    assertNotNull(persistedLesson);
    assertEquals("Understanding Firewalls", persistedLesson.getTitle());
    assertEquals("A deep dive into firewalls", persistedLesson.getDescription());
    assertEquals(3, persistedLesson.getSections().size());

    // Drill down to parent section
    LessonSection persistedParentSection = persistedLesson.getSections().get(0);
    assertNotNull(persistedParentSection);
    assertEquals("Firewall Basics", persistedParentSection.getTitle());
    assertEquals(2, persistedParentSection.getSubsections().size());

    // Verify child sections
    LessonSection persistedChildSection1 = persistedParentSection.getSubsections().get(0);
    assertNotNull(persistedChildSection1);
    assertEquals("Packet Filtering", persistedChildSection1.getTitle());
    assertEquals(persistedParentSection,
        persistedChildSection1.getParent()); // Verify bidirectional reference

    LessonSection persistedChildSection2 = persistedParentSection.getSubsections().get(1);
    assertNotNull(persistedChildSection2);
    assertEquals("Stateful Inspection", persistedChildSection2.getTitle());
    assertEquals(persistedParentSection,
        persistedChildSection2.getParent()); // Verify bidirectional reference
  }

  // Test orphan removal when a parent LessonSection is removed
  @Test
  void testOrphanRemovalForNestedLessonSections() {
    EntityTransaction transaction = entityManager.getTransaction();

    // Create hierarchy of LessonSections
    Course course = createCourse("Operating Systems", "Deep dive into OS internals", "os.jpg");
    CourseModule module = createCourseModule(course, "Processes and Threads",
        "Learn about threads, processes, and their management", "processes.jpg");
    Lesson lesson = createLesson(module, "Thread Management", "Managing threads effectively");

    LessonSection parentSection = createLessonSection(lesson, "Introduction to Threads");
    LessonSection childSection =
        createLessonChildSection(lesson, parentSection, 1, "Thread Synchronization");
    entityManager.persist(course);
    transaction.commit();
    entityManager.clear();

    // Remove the parent section and verify orphan removal of child section
    transaction.begin();
    Lesson persistedLesson =
        entityManager.createQuery("SELECT l FROM lessons l WHERE l.title = :title", Lesson.class)
                     .setParameter("title", "Thread Management")
                     .getSingleResult();
    persistedLesson.getSections().remove(0); // Remove the parent section
    transaction.commit();
    entityManager.clear();

    // Verify parent and child sections are removed
    LessonSection deletedParent = entityManager.find(LessonSection.class, parentSection.getId());
    LessonSection deletedChild = entityManager.find(LessonSection.class, childSection.getId());

    assertNull(deletedParent); // Parent should be deleted
    assertNull(deletedChild);  // Child should also be deleted as it's orphaned
  }

  // Test cascading delete for deeply nested LessonSections
  @Test
  void testCascadeDeleteWithDeeplyNestedSections() {
    EntityTransaction transaction = entityManager.getTransaction();

    // Create a deeply nested structure
    Course course = createCourse("Cloud Computing", "Master cloud technologies", "cloud.jpg");
    CourseModule module =
        createCourseModule(course, "AWS Fundamentals", "Learn the basics of AWS", "aws.jpg");
    Lesson lesson = createLesson(module, "AWS EC2", "Managing virtual machines on AWS");

    LessonSection section1 = createLessonSection(lesson, "Launch EC2");
    LessonSection subsection1 = createLessonChildSection(lesson, section1, 1, "Instance Types");
    LessonSection subsection2 = createLessonChildSection(lesson, section1, 2, "Key Pair Setup");

    entityManager.persist(course);
    transaction.commit();
    entityManager.clear();

    // Delete the lesson and ensure cascading delete propagates to all sections and subsections
    transaction.begin();
    Lesson persistedLesson =
        entityManager.createQuery("SELECT l FROM lessons l WHERE l.title = :title", Lesson.class)
                     .setParameter("title", "AWS EC2")
                     .getSingleResult();
    entityManager.remove(persistedLesson);
    transaction.commit();
    entityManager.clear();

    // Verify sections and subsections are also deleted
    LessonSection deletedSection1 = entityManager.find(LessonSection.class, section1.getId());
    LessonSection deletedSubsection1 = entityManager.find(LessonSection.class, subsection1.getId());
    LessonSection deletedSubsection2 = entityManager.find(LessonSection.class, subsection2.getId());

    assertNull(deletedSection1);
    assertNull(deletedSubsection1);
    assertNull(deletedSubsection2);
  }

  private static LessonSection createLessonChildSection(Lesson lesson, LessonSection parent,
      int order, String title) {
    LessonSection section = new LessonSection();
    section.setId(null);
    section.setIndex(order);
    section.setTitle(title);
    section.setParent(parent);
    section.setLesson(lesson);
    parent.addSubsection(section); // Maintain parent-child relationship
    return section;
  }

  private static LessonSection createLessonSection(Lesson lesson, String title) {
    LessonSection section = new LessonSection();
    section.setId(null);
    section.setIndex(1);
    section.setTitle(title);
    section.setLesson(lesson);
    lesson.addSection(section); // Add to lesson's main sections
    return section;
  }

  private static Course createCourse(String title, String description, String imageUrl) {
    Course course = new Course();
    course.setId(null);
    course.setIndex(1);
    course.setTitle(title);
    course.setDescription(description);
    course.setImageUrl(imageUrl);
    return course;
  }

  private static CourseModule createCourseModule(Course course, String title, String description,
      String imageUrl) {
    CourseModule module = new CourseModule();
    module.setId(null);
    module.setCourse(course);
    module.setTitle(title);
    module.setDescription(description);
    module.setImageUrl(imageUrl);
    course.addModule(module);
    return module;
  }

  private static Lesson createLesson(CourseModule module, String title, String description) {
    Lesson lesson = new Lesson();
    lesson.setId(null);
    lesson.setModule(module);
    lesson.setIndex(1);
    lesson.setTitle(title);
    lesson.setDescription(description);
    module.addLesson(lesson);
    return lesson;
  }

}