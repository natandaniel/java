# Validating Persistent Fields And Properties

## 1. Overview

- **Bean Validation**: The Java API for JavaBeans Validation provides an effective mechanism to validate data within
  JPA (Java Persistence API) applications.
- **Integration with JPA**:
    - Integrated with persistence providers to validate entity data at specific lifecycle events.
    - Provides unified validation logic across entity classes, embeddable classes, and mapped superclasses in a JPA
      context.

---

## 2. Using Bean Validation Constraints in JPA

- **Constraint Placement**: Constraints are annotations placed on persistent fields or properties of a JPA entity or
  embeddable class.
- **Constraint Types**:
    - **Built-In Constraints**: Defined in the `jakarta.validation.constraints` package.
    - **Custom Constraints**: User-defined constraints with specific validation logic.

**Table of Built-In Constraints**:

| Constraint     | Description                                                                                                           | Example                                                            |
|----------------|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| `@AssertFalse` | The field or property value must be false.                                                                            | `@AssertFalse boolean isUnsupported;`                              |
| `@AssertTrue`  | The field or property value must be true.                                                                             | `@AssertTrue boolean isActive;`                                    |
| `@DecimalMax`  | Value must be decimal and <= the specified number.                                                                    | `@DecimalMax("30.00") BigDecimal discount;`                        |
| `@DecimalMin`  | Value must be decimal and >= the specified number.                                                                    | `@DecimalMin("5.00") BigDecimal discount;`                         |
| `@Digits`      | Value must match specified range of digits for integer and fraction.                                                  | `@Digits(integer=6, fraction=2) BigDecimal price;`                 |
| `@Future`      | Value must be a date in the future.                                                                                   | `@Future Date eventDate;`                                          |
| `@Max`         | Value must be an integer <= the specified number.                                                                     | `@Max(10) int quantity;`                                           |
| `@Min`         | Value must be an integer >= the specified number.                                                                     | `@Min(5) int quantity;`                                            |
| `@NotNull`     | Value must not be null.                                                                                               | `@NotNull String username;`                                        |
| `@Null`        | Value must be null.                                                                                                   | `@Null String unusedString;`                                       |
| `@Past`        | Value must be a date in the past.                                                                                     | `@Past Date birthday;`                                             |
| `@Pattern`     | Value must match the defined regular expression.                                                                      | `@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}") String phoneNumber;` |
| `@Size`        | Size of the field or property must match the specified boundaries. Applies to Strings, Arrays, Collections, and Maps. | `@Size(min=2, max=240) String briefMessage;`                       |

---

## 3. Application of Bean Validation in JPA

- **Target Classes**:
    - Persistent JPA entity classes.
    - Embeddable classes.
    - Mapped superclasses.
- **Lifecycle Validation**:
    - Persistence providers automatically enforce constraints during JPA lifecycle events such as:
        - `PrePersist`: Before persisting a new entity.
        - `PreUpdate`: Before updating an existing entity.
        - `PreRemove`: Before removing an entity.

---

## 4. Constraints and JPA Field or Property Access

- **Consistency with Access Strategy**:
    - If using **field access**, apply constraints on class fields directly.
    - If using **property access**, apply constraints on the getter methods.

---

## 5. Custom Constraints in JPA

- **Definition**: Custom constraints allow you to add validation logic specific to your application's requirements.
- **Components**:
    - A custom annotation for the constraint.
    - A validator class implementing the validation logic.
- **Usage**:
    - Custom constraints can validate complex business rules like specific email address formats, unique identifiers,
      etc.

---

## 6. Key Features for JPA Applications

- **Built-In Constraints**: Provide an extensive range of validations for common scenarios such as null checks, ranges,
  date validations, and regular expression matching.
- **Custom Constraints**: Grant flexibility to implement custom logic tailored to the application or domain-specific
  requirements.
- **Lifecycle Awareness**:
    - Constraints are automatically validated during JPA lifecycle events without additional code.
- **Unified Validation Logic**: Ensures data consistency and correctness across all layers accessing the JPA entities.

---

## 7. Advantages of Bean Validation in JPA

- Simplifies the codebase by moving validation logic to field-level annotations.
- Ensures consistency across application layers, allowing shared validation rules in entities, services, and database
  operations.
- Reduces boilerplate code by leveraging annotation-driven built-in constraints.
- Easily extendable with custom-defined constraints for more complex validation needs.