# Online Learning Platform Course Domain

This sample code explores modeling approaches for course content management in an online learning platform.

## v1: Explicit modeling

In **v1**, a structured, type-safe approach is implemented using polymorphism and inheritance.

### Structure

- `LessonContent` is an abstract class representing a general content type, with specific subtypes:
    - `LessonText` – Represents textual content.
    - `LessonCode` – Represents code examples (`language` and `code`).
    - `LessonFigure` – Represents figures, such as images, with fields like `alt`, `title`, and `url`.
- `LessonSection` organizes `LessonContent` into hierarchical sections within a lesson.
- Relations between entities are modeled using JPA annotations like `@OneToMany` and `@ManyToOne`.

### Pros

- **Strong validation**: Each content type is represented explicitly for stricter validations.
- **Clear structure**: Separate types make responsibilities distinct and well-defined.
- **Extensibility**: Behavior unique to specific content types can easily be added.

### Cons

- **Complex schema**: Requires multiple tables for different content types (`lesson_texts`, `lesson_code_samples`,
  etc.).
- **Rigid**: Adding new content types requires schema changes and extensive adjustments.

## v2: Single text unit stored in `@Lob`

In **v2**, the model is simplified by storing the lesson content directly as text in a single database field.

### Structure

- The `Lesson` entity contains:
    - Metadata such as `title` and `description`.
    - A `content` field, which uses `@Lob` to store data representing all types of lesson content.

### What is `@Lob`?

- `@Lob` (Large Object) is a JPA annotation used to store large amounts of data.
- In this sample code, the `content` field leverages `@Lob` to store data as a `CLOB` (Character Large Object), enabling
  flexible storage of structured JSON strings.

### Pros

- **Simple schema**: All lesson data is contained within a single `Lesson` entity and table.
- **Flexible**: New content types can be added or updated without altering the database schema.
- **Efficient**: Eliminates the need for multiple joins during queries.

### Cons

- **Weaker validation**: Validation of text structure must be handled at the application level, as the database does not
  provide built-in constraints for validating the format or structure.
- **Limited querying**: Text content, like JSON, cannot be natively queried within traditional SQL databases. Advanced
  queries may require additional processing or custom logic.
- **Risk of inconsistencies**: Without a strict schema or database-level validation, there is a higher risk of
  inconsistencies in how the content is stored and interpreted.
- **Potential performance issues**: Handling very large text fields could lead to performance degradation for certain
  database operations, such as updates or searches.

## v3: Lesson content stored as a URL

In **v3**, we introduce a solution where the lesson content itself is hosted externally (e.g., on a blob storage
service), and only the URL to the content is persisted in the database.

### Structure

- The `Lesson` entity in the database stores:
    - Metadata like `title` and `description`.
    - A `contentUrl` field that references the externally hosted HTML file for the lesson.

- Static files, such as HTML content, images, and videos, are uploaded separately to an object storage solution (e.g.,
  AWS S3, Azure Blob Storage).

### Pros

- **Separation of Concerns**: Content hosting is offloaded to a scalable, performant blob storage system, while the
  database focuses solely on metadata.
- **Scalable**: Blob storage systems are optimized for high-performance and large-scale content delivery.
- **Simpler schema**: The database model is leaner and only tracks basic metadata and external URLs.
- **Content updates**: Easy to update hosted content without changing the database schema or data.

### Cons

- **Cannot search hosted content**: Lesson content cannot be natively queried or indexed in SQL.
- **Storage dependency**: External storage services must be configured, managed, and secured.