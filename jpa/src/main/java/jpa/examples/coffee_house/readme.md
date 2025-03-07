# Coffee House JPA Sample Project

This sample project leverages JPA features to model and manage the domain of a Coffee House business. It includes
various classes, relationships, and configurations to demonstrate the flexibility and capabilities of JPA.

## Features Explored

### 1. Entity Mapping

- **`@Entity`**: Classes like `CoffeeHouse`, `CoffeeBeans`, `Sale`, etc., are mapped as database entities to manage
  persistence.

### 2. Embeddable Objects

- **`@Embedded` and `@Embeddable`**: Reusable components for richer entity modeling.
    - `Address` (used in `CoffeeHouse` to store location details)
    - `Product` (used in `CoffeeBeans`, `CoffeeMaker`, and `CoffeeDrink` for common product attributes)

### 3. Enumerations

- **`@Enumerated(EnumType.STRING)`**: Maps Java enums to database fields as strings for readability and future
  extensibility.
    - E.g., `coffeeBeanType` in `CoffeeBeans` or `coffeeMakerType` in `CoffeeMaker`.

### 4. Relationships

- **`@OneToMany` and `@ManyToOne`**:
    - `CoffeeHouse` to `CoffeeBeans`, `CoffeeMaker`, and `Sale` uses a **One-To-Many** relationship to manage
      collections of related entities.
    - `CoffeeBeans`, `CoffeeMaker`, and `Sale` link back to `CoffeeHouse` with a **Many-To-One** relationship.
- **Mapped Collections using `@MapKeyColumn`**:
    - Maps entities like `CoffeeBeans` and `CoffeeMakers` by a unique key (`name`) for efficient retrieval.

### 5. Lifecycle Management with `EntityManager`

- The project demonstrates lifecycle operations (create, read, update, delete) with `EntityManager`.
    - **Persist**: To save new entities (`entityManager.persist()`).
    - **Merge**: To update existing entities (`entityManager.merge()`).
    - **Remove**: To delete entities (`entityManager.remove()`).
- JPQL queries are used for custom operations like finding a `CoffeeHouse` by name.

### 6. Collection and Cascade Management

- **Cascade Operations**: Automatically propagate operations like persist or delete across associated entities.
- **Orphan Removal**: Ensures dependent entities are removed when they are no longer referenced.

### 7. Data Validation and Constraints

- Logical operations and validation are managed within entities.
    - E.g., `CoffeeHouse` validates stock management for coffee beans and ensures valid operations like restocking or
      selling.

## Package Structure

### Entities

- `CoffeeHouse`: Represents the central business entity.
- `CoffeeBeans`, `Sales`, `CoffeeDrink`, `CoffeeMaker`: Represent specific elements of the business.
- `Supplier`: Manages suppliers for the Coffee House.

### Embeddable Classes

- `Address`: Stores location details for the `CoffeeHouse`.
- `Product`: A reusable abstraction for product attributes like name, price, and description.

### Enums

- `CoffeeBeanType` and `CoffeeMakerType`: Represent predefined, structured options used across entities.

### Repository

- `CoffeeHouseRepository`: Demonstrates CRUD operations and entity query methods.