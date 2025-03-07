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

## Comparison of v1 and v2

| **Aspect**               | **v1 (Explicit Modeling)**                    | **v2 (JSON-Based Model)**              |
|--------------------------|-----------------------------------------------|----------------------------------------|
| **Content Flexibility**  | Low; new types require code/schema changes.   | High; new types can be added via JSON. |
| **Complexity**           | High; multiple entities and tables.           | Low; single JSON column.               |
| **Validation & Control** | Strong; enforced through JPA.                 | Weak; relies on JSON validation.       |
| **Query Performance**    | Complex; involves joins for polymorphic data. | Simplified; content is consolidated.   |
| **Purpose**              | Explore JPA inheritance and polymorphism.     | Prioritize flexibility and simplicity. |