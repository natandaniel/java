# Online Learning

This sample code explores modeling approaches for course content management in an online learning platform. It showcases
different strategies for storing and managing course, module, and lesson content efficiently using JPA.

## Features Explored

### 1. Entity Mapping

- **`@Entity`**: Classes such as `Course`, `CourseModule`, `Lesson`, `LessonSection`, and `LessonContent` are annotated
  as entities to manage persistence for the course domain.

### 2. Relationships

- **`@OneToMany` and `@ManyToOne`**:
    - `Course` to `CourseModule`, and `CourseModule` to `Lesson` relationships use **One-To-Many** mapping, reflecting a
      hierarchical structure between entities.
    - `Lesson` to `LessonSection` and `LessonContent` illustrates nested parent-child relationships for lesson data
      organization.
- These relationships maintain referential integrity between related database tables.

### 3. Enumerations

- **`@Enumerated(EnumType.STRING)`**: Enums are used for specific fields where structured values need to be represented
  as readable strings in the database, avoiding hardcoded values in entities.

### 4. Validation

- Validation is incorporated at the entity level using annotations to enforce constraints on data. For example:
    - **`@Pattern`**: Defines regular expression patterns for validating fields like content URLs or text formats.
    - **`@NotNull` / `@NotBlank`**: Ensures required fields like `Course.title` or `LessonContent.text` are mandatory
      and cannot be null or blank.
    - **`@Size`**: Enforces constraints on the minimum or maximum size of collections or strings, e.g.,
      `CourseModule.lessons`.
- These constraints ensure data consistency and prevent invalid values from being persisted.

### 5. Data Types and Optimizations

- **`@Lob`**: Used for storing large text fields, such as JSON-encoded content for lessons or modules, allowing flexible
  storage of unstructured or semi-structured data.
- The project explores integrating externally hosted content via URLs for scalable solutions using fields like
  `Lesson.contentUrl`.

### 6. Lifecycle Management with `EntityManager`

- The sample demonstrates typical JPA lifecycle operations for entity persistence and management, including:
    - **Persist**: Save a new entity, e.g., `entityManager.persist(new Lesson())`.
    - **Merge**: Update an existing entity, e.g., `entityManager.merge(courseModule)`.
    - **Remove**: Delete an entity, e.g., `entityManager.remove(lesson)`.

## Comparison of Modeling Approaches

### v1: Explicit Modeling

This version explicitly models content types and their attributes with separate, dedicated entity classes.

#### Structure

- `LessonContent` is an abstract base class with specific subclasses:
    - `LessonText`: Manages textual content.
    - `LessonCode`: Handles code snippets with attributes such as `language` and `code`.
    - `LessonFigure`: Represents media files like images or diagrams with fields for `url`, `alt`, and `title`.
- `LessonSection` organizes these content types hierarchically.
- Relationships between entities are implemented using JPA annotations such as `@OneToMany` and `@ManyToOne`.

#### Pros

- **Strong Validation**: Explicit definitions allow for stricter validation of each content type.
- **Clear Structure**: Each content type is well-defined with clear separation of responsibility.
- **Extensibility**: Adding new behaviors or attributes specific to one content type is straightforward.

#### Cons

- **Rigid Schema**: Adding new content types requires schema changes and corresponding code updates.
- **Complex Schema**: Results in multiple tables (one for each content type), which increases database complexity.

### v2: Using `@Lob` for Unified Content Storage

This approach simplifies the model by storing different content types as a single large object (e.g., JSON) within the
`Lesson` entity.

#### Structure

- The `Lesson` entity includes:
    - Metadata fields like `title` and `description`.
    - A `content` field annotated with `@Lob`, which stores lesson content in a unified JSON format.

#### Pros

- **Simplified Schema**: Collapses multiple tables into a single `Lesson` table.
- **Flexible**: Adding or updating content types does not require schema modifications as changes are handled within the
  JSON structure.
- **Less Overhead**: Avoids managing relationships between various content type entities.

#### Cons

- **Weaker Validation**: The database cannot enforce schema constraints or validate JSON content—this must be handled in
  the application layer.
- **Limited Querying**: Advanced querying of JSON data is difficult using traditional SQL, requiring additional custom
  logic.
- **Risk of Format Inconsistencies**: Inconsistent data formats can accumulate over time without a strict schema.

### v3: Hosting Content Externally (URL-Based)

In this version, lesson content is hosted externally (e.g., in a cloud storage service), and only the reference URL is
stored in the database.

#### Structure

- The `Lesson` entity manages:
    - Metadata such as `title` and `description`.
    - A `contentUrl` field that refers to externally hosted static files (e.g., an HTML page, media, or files stored in
      AWS S3 or Azure Blob Storage).

#### Pros

- **Scalable**: Relieves the database of storing large content files by offloading them to efficient, scalable blob
  storage.
- **Separation of Concerns**: Focuses the database on metadata and uses external systems for managing actual content.
- **Simpler Schema**: Minimal database schema simplifies data management, reducing complexity.

#### Cons

- **Search Limitations**: Hosted content cannot be searched directly through the database.
- **External Dependency**: Relies on external storage services, leading to potential network latency and operational
  costs.
- **Synchronization Challenges**: Ensuring URLs stored in the database remain synchronized with externally hosted
  content.

## Package Structure

### Entities

- **`Course`**: Represents an online course that contains one or more course modules. Includes metadata fields such as
  `title`, `description`, and `duration`.
- **`CourseModule`**: Represents a module within a course. Each module includes multiple lessons and metadata such as a
  `moduleTitle`.
- **`Lesson`**: Represents an individual lesson, containing metadata (`title`, `description`) and the lesson’s content.
- **`LessonSection`**: Nested sections within a lesson, organizing the corresponding lesson content in structured parts.
- **`LessonContent`**: The base entity for specific content types like `LessonText`, `LessonCode`, and `LessonFigure`.

### Relationships

- **`Course` ↔ `CourseModule`**: Each course can contain multiple modules, with each module linked back to a single
  course.
- **`CourseModule` ↔ `Lesson`**: Modules manage collections of lessons, reflecting their hierarchical nature.
- **`Lesson` ↔ `LessonSection` and `LessonContent`**: Lessons are divided into sections and associated with various
  content types for flexible organization.

### Data Validation

- **Field-Level Validation**:
    - **`@Pattern`:** Applied to fields like `Lesson.contentUrl` to ensure valid URL formats.
    - **`@NotBlank`:** Used for mandatory strings like `Course.title` or `Lesson.title` to enforce required fields.
    - **`@Size`:** Limits collection sizes (e.g., minimum and maximum number of modules in a course).
- **Hierarchical Validation**:
    - Validation of `Course` ensures it contains at least one `CourseModule`.
    - Validation of `LessonContent` checks for essential fields depending on the content type (e.g., `code` for
      `LessonCode` must not be null).