# Online Learning Platform Course Domain

This sample code explores modeling approaches for course content management in an online learning platform. It showcases
different strategies for storing and managing lesson content efficiently using JPA.

## Features Explored

### 1. Entity Mapping

- **`@Entity`**: Classes like `Lesson`, `LessonContent`, `LessonSection`, etc., are mapped as database entities to
  manage persistence for the course domain.

### 2. Embeddable Objects

- **`@Embedded` and `@Embeddable`**: Reusable components are implemented to add flexibility to certain entities, such as
  metadata associated with lessons or content sections.

### 3. Relationships

- **`@OneToMany` and `@ManyToOne`**:
    - `Lesson` to `LessonSection` or `LessonContent` demonstrates a **One-To-Many** relationship for organizing the
      hierarchical structure of lessons and their associated data.
    - Subcomponents like text, code, and media establish a **Many-To-One** back-reference to their parent, such as
      `LessonSection`.

### 4. Lifecycle Management with `EntityManager`

- The sample code demonstrates typical lifecycle operations for managing entities:
    - **Persist**: To save new entities (`entityManager.persist()`).
    - **Merge**: To update existing entities (`entityManager.merge()`).
    - **Remove**: To delete entities (`entityManager.remove()`).

### 5. Data Types and Optimizations

- **`@Lob`**: Used to store large lesson contents (e.g., textual data or JSON).
    - Demonstrated as a flexible way to handle diverse types of content, simplifying schema design for unstructured or
      semi-structured data.
- Blob storage with external systems is used for hosting content referenced via URLs.

### 6. Data Validation and Constraints

- Validation at the entity and database levels ensures that the stored data remains consistent.
    - For example, hierarchical content dependencies like `Lesson` and `LessonSection` are protected using JPA
      relationships.

## Comparison of Modeling Approaches

### v1: Explicit Modeling

In this version, lesson content types are explicitly modeled as separate classes with a clear and type-safe
implementation.

#### Structure

- `LessonContent` serves as an abstract base class with specific subtypes:
    - `LessonText`: For textual content.
    - `LessonCode`: For code samples (`language` and `code`).
    - `LessonFigure`: For images or figures with fields like `alt`, `title`, and `url`.
- `LessonSection` helps organize content types hierarchically.
- Relationships between lessons, sections, and their respective content are handled using JPA annotations like
  `@OneToMany` and `@ManyToOne`.

#### Pros

- **Strong Validation**: Explicit types ensure stricter validations at the code and database levels.
- **Clear Structure**: By separating content types, responsibilities are distinct and well-defined.
- **Extensibility**: New behaviors unique to a particular content type can be added easily.

#### Cons

- **Complex Schema**: Requires multiple tables for different content types (`lesson_texts`, `lesson_code`, etc.).
- **Rigid**: Schema changes and code updates are needed to add new content types.

### v2: Single Text Unit Using `@Lob`

In this version, lesson data is stored as text in one database column using the `@Lob` annotation.

#### Structure

- The `Lesson` entity contains:
    - Metadata such as `title` and `description`.
    - A centralized `content` field that stores any type of lesson content in a structured format (e.g., JSON or a
      custom markup).

#### Features of `@Lob`

- **Large Object Storage**: Enables storing large textual or binary data types (CLOB/BLOB) directly in the database.

#### Pros

- **Simpler Schema**: Reduces the complexity of the database design by collapsing multiple tables into a single entity.
- **Flexible**: New content types can be added without modifying the database schema.
- **Efficient**: Eliminates the need for frequent joins across multiple tables during queries.

#### Cons

- **Weaker Validation**: The database does not validate the structure of the stored content, so it needs to be enforced
  in the application code.
- **Limited Querying**: Cannot perform complex queries against unstructured data like JSON using traditional SQL.
- **Inconsistencies**: Higher risk of discrepancies due to the flexibility of the content format.

### v3: Content Stored Externally (URL-Based)

In this approach, lesson content is hosted externally (e.g., in object storage like AWS S3 or Azure Blob Storage), with
the database storing the reference URLs.

#### Structure

- `Lesson` holds:
    - Metadata like `title` and `description`.
    - A `contentUrl` field pointing to the external hosted resource.
- Content such as HTML, images, and videos are stored in an object storage system optimized for large-scale content
  delivery.

#### Pros

- **Separation of Concerns**: Focuses the database on metadata while offloading content storage to scalable systems.
- **Scalable**: Designed for high-performance storage and retrieval of large content.
- **Simpler Schema**: Minimal database schema reduces management complexity.
- **Content Updates**: Hosted content can be updated without modifying any database entries.

#### Cons

- **Search Limitations**: Hosted content cannot be searched or queried directly from the database.
- **External Dependency**: Relies on external storage systems, adding potential network latency.
- **Data Synchronization**: Requires careful management to ensure the database and hosted content remain consistent.

## Package Structure

### Entities

- `Lesson`: Represents the main lesson entity containing metadata and content.
- `LessonContent`: Abstract base class for various content types.
- `LessonSection`: Organizes `LessonContent` into sections within each lesson.