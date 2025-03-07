# Online learning platform Course domain

## Introduction

This package models some entities and their relationships for an online learning platform course domain. It represents
Courses, Modules, Lessons, Lesson Sections, and their associated content types, while
ensuring the system remains extensible, maintainable, and aligned with best practices in JPA and domain-driven design.

## Key Design Choices

### 1. **BaseEntity Inheritance**

- **Why**: To standardize common attributes (`id` and `index`) across all entities and reduce boilerplate code.
- **Implementation**:
    - A `BaseEntity` class is defined as a `@MappedSuperclass`.
    - All entities inherit these attributes for consistency.
- **Impact**: This allows uniform ID generation (`GenerationType.IDENTITY`) and indexing functionality across various
  tables, fostering easier maintenance.

### 2. **Separation of Concerns in Course Structure**

The course structure follows a hierarchical design:

- **Course → CourseModule → Lesson → LessonSection**:
    - **Course**: Represents the overarching course.
    - **CourseModule**: Groups related lessons under a single module within a course.
    - **Lesson**: Represents individual learning units.
    - **LessonSection**: Breaks lessons into sections and supports subsections for finer granularity.
- **Reasoning**:
    - Provides a clear hierarchy to organize the learning content.
    - Facilitates future scalability, e.g., adding course progress tracking or prerequisites at any level.

### 3. **LessonContent Abstraction**

- **What**: Abstract class `LessonContent` representing different types of content in a lesson section.
    - Subclasses include:
        - `LessonText` (text content)
        - `LessonCode` (code samples with programming language metadata)
        - `LessonFigure` (includes image metadata such as `alt` text, title, and URL).
- **Why**:
    - Abstracting content allows **polymorphic behavior** while separating data concerns.
    - Using `@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)` minimizes table joins when querying content.
    - The ID generation strategy `@GeneratedValue(strategy = GenerationType.TABLE)` is applied here to ensure unique
      identifiers for each subclass across separate tables.
- **Impact**:
    - Easier to introduce new content types in the future by simply creating a new class inheriting `LessonContent`.
    - Unique identifiers are maintained efficiently across the polymorphic hierarchy.

### 4. **Bidirectional Relationships**

- Relationships are modeled to allow **bidirectional navigation**, achieving flexibility:
    - **Course ←→ CourseModule**
        - Courses contain multiple modules, and each module refers back to its parent course.
    - **CourseModule ←→ Lesson**
        - Lessons belong to a module, and modules can track all their lessons.
    - **Lesson ←→ LessonSection**
        - Lessons are broken into sections, and sections can track their parent lesson.
    - **LessonSection ←→ Subsections/LessonContent**
        - Lesson sections can have nested subsections or associated content (text, code, images, etc.).
- Relationships use `@ManyToOne` and `@OneToMany` with `cascade = CascadeType.ALL` to:
    - Automatically manage persistence of dependent entities.
    - Ensure orphaned entities (e.g., deleted modules, lessons) are removed from the database.
- **Impact**:
    - Navigation between entities is seamless in both directions.
    - Maintenance remains straightforward, as cascading simplifies entity lifecycle management.

### 5. **Validation and Constraints**

- Validation annotations are used to enforce data integrity:
    - Example:
        - `@NotBlank`, `@Size`, and `@Pattern` ensure text fields like `title`, `imageUrl`, etc., are validated.
        - Code samples and image URLs must maintain their expected format.
- **Impact**:
    - Guards against invalid input and ensures that persisted data adheres to business rules.

### 6. **Organized Storage of Lessons and Their Components**

- **LessonSection** includes:
    - Nested subsections (to organize hierarchical content within lessons).
    - Associated lesson content (`LessonContent`) to store diverse content formats.
- **Impact**:
    - Provides the ability to represent complex lessons with rich content, subsections, or even nested structures.

## Key Considerations

### **Extensibility**

- The hierarchy of `LessonContent` supports future content types without requiring changes to the existing database
  schema.
- Example: A `LessonVideo` class (e.g., for embedding videos) could extend `LessonContent` seamlessly.

### **Performance**

- `@OneToMany` relationships (`fetch = FetchType.EAGER` where required, e.g., `Course -> Modules`) ensure essential data
  is fetched upfront.
- However, lazy fetching is also applied where applicable to minimize query overhead.

### **Database Modeling**

- Leveraging JPA inheritance (`@Inheritance`) avoids duplicating content logic while ensuring table-level clarity.
- Using cascades and `orphanRemoval` reduces manual management of related entities in the database.

## Conclusion

The `course` subpackage was designed with a focus on clarity, extensibility, and maintainability. By leveraging
established JPA patterns, abstracting shared logic, and organizing entities hierarchically, the design provides a robust
foundation to build and evolve the platform's course and lesson management features over time.