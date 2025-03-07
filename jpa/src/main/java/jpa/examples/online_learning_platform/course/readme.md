# Online Learning Platform Course Domain

## Design Overview

### v1: Explicit Modeling

- Each lesson component (`LessonText`, `LessonCode`, `LessonFigure`) extends an abstract class `LessonContent`.
- Designed to explore JPA features such as inheritance (`@Inheritance`) and polymorphism.
- Strict validation and strong typing.
- Adds complexity with multiple entities and tables.
- Limited flexibility: adding new content types requires code and schema changes.

### v2: JSON-Based Model

- Lesson content is stored in a single `content` field using JSON (`@Column(columnDefinition = "jsonb")`).
- Simplifies the model: no separate classes or tables for content types.
- Flexible: new content types can be added dynamically without modifying the domain model.
- Weaker validation: relies on external JSON schema validation.