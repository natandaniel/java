# Online Learning Platform Course Domain

Here I explore two distinct modeling approaches for managing course content in an online learning platform: **explicit
modeling** (v1) and **JSON-based modeling** (v2).

---

## v1: Explicit Modeling

In **v1**, a structured, type-safe approach is used with polymorphism and inheritance.

### Structure

- `LessonContent` is an abstract class with specific subtypes:
    - `LessonText` (text content)
    - `LessonCode` (code examples with `language` and `code`)
    - `LessonFigure` (figures with `alt`, `title`, and `url`)
- `LessonSection` organizes contents hierarchically.
- Relations between entities are modeled using `@OneToMany` and `@ManyToOne`.

### Pros

- Strong validation and clear responsibilities through explicit types.
- Extensible with behavior unique to each content type.

### Cons

- Complex database schema with multiple tables (`lesson_texts`, `lesson_code_samples`, etc.).
- Adding new content types requires schema changes.

---

## v2: JSON Stored in `@Lob`

In **v2**, a simpler, more flexible approach stores lesson content directly as JSON in a single field.

### Structure

- The `Lesson` entity combines metadata (e.g., `title`, `description`) with a `content` field stored as a JSON string
  using `@Lob`.

### Pros

- Simple schema with one `Lesson` table.
- Highly flexible: Adding new content types does not require schema changes.

### Cons

- Relies on JSON validation in the application, which is less strict than type-based modeling.
- Limited SQL query capabilities since content is stored as unstructured data.