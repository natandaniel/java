<!-- TOC -->

* [Entity Inheritance](#entity-inheritance)
    * [1. Abstract Entities](#1-abstract-entities)
    * [2. Mapped Superclasses](#2-mapped-superclasses)
    * [3. Non-Entity Superclasses](#3-non-entity-superclasses)
    * [4. Entity Inheritance Mapping Strategies](#4-entity-inheritance-mapping-strategies)
        * [4.1 Single Table per Class Hierarchy (Default)](#41-single-table-per-class-hierarchy-default)
        * [4.2 Table per Concrete Class](#42-table-per-concrete-class)
        * [4.3 Joined Subclass Strategy](#43-joined-subclass-strategy)
    * [Summary of Mapping Strategies](#summary-of-mapping-strategies)

<!-- TOC -->

# Entity Inheritance

Entities support **class inheritance**, **polymorphic associations**, and **polymorphic queries**. Entity classes can
extend non-entity classes and vice-versa. Entity classes can be **abstract** or **concrete**.

---

## 1. Abstract Entities

- Declared with `@Entity`.
- **Cannot be instantiated**.
- Can be **queried** like concrete entities.
- Queries targeting abstract entities operate on all subclasses.

**Example:**

```java

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single-table inheritance strategy
@DiscriminatorColumn(name = "VEHICLE_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {
  @Id
  @GeneratedValue
  private Long id;

  private String manufacturer;

  // Getters and setters...
}

@Entity
@DiscriminatorValue("Car") // Explicit discriminator value
public class Car extends Vehicle {
  private int numberOfDoors;

  // Getters and setters...
}

@Entity
@DiscriminatorValue("Truck")
public class Truck extends Vehicle {
  private double payloadCapacity;

  // Getters and setters...
}
```

In this example:

- `Vehicle` is an abstract entity that **cannot be instantiated**.
- Subclasses `Car` and `Truck` extend `Vehicle` and are queryable via the `Vehicle` entity.

---

## 2. Mapped Superclasses

- Declared with `@MappedSuperclass`.
- Used to share common state and mapping information without being an entity.
- **Characteristics**:
    - Cannot be queried or used in `EntityManager` operations.
    - Cannot be targets of entity relationships.
    - Do not have corresponding tables in the database.
    - Subclasses define their own table mappings.
    - Can be **abstract** or **concrete**.

**Example:**

```java

@MappedSuperclass
public abstract class AuditModel {
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  // Getters and setters...
}

@Entity
public class User extends AuditModel {
  @Id
  @GeneratedValue
  private Long id;

  private String username;

  // Getters and setters...
}

@Entity
public class Product extends AuditModel {
  @Id
  @GeneratedValue
  private Long id;

  private String productName;

  // Getters and setters...
}
```

In this example:

- `AuditModel` provides common properties (`createdAt`, `updatedAt`) but is not an entity itself.
- Subclasses `User` and `Product` inherit `AuditModel`’s fields and define their own database tables.

---

## 3. Non-Entity Superclasses

- Superclasses that are not annotated as entities.
- **Characteristics**:
    - Inherited state is **non-persistent**.
    - Cannot be used in `EntityManager` or query operations.
    - Mapping or relationship annotations are ignored.

**Example:**

```java
public abstract class Person {
  private String name;
  private String address;

  // Getters and setters...
}

@Entity
public class Customer extends Person {
  @Id
  @GeneratedValue
  private Long id;

  private String loyaltyCardNumber;

  // Getters and setters...
}
```

In this example:

- `Person` has attributes like `name` and `address` that are **not persisted** in the database.
- Only `Customer` is considered a JPA entity and can be used in persistence operations.

---

## 4. Entity Inheritance Mapping Strategies

Determines how the persistence provider maps inherited entities into the database. It is configured using `@Inheritance`
on the root class of the hierarchy. The available strategies include:

### 4.1 Single Table per Class Hierarchy (Default)

- A **single table** is used for all classes in the hierarchy.
- **Discriminator column**:
    - Configured with `@DiscriminatorColumn`.
    - Default name: `DTYPE`.
    - Default type: `DiscriminatorType.STRING`.
- **Discriminator values**:
    - Configured using `@DiscriminatorValue`.
    - Default (if not specified):
        - For `STRING`: the entity class name.

**Example:**

```java

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ANIMAL_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Animal {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
}

@Entity
@DiscriminatorValue("Dog")
public class Dog extends Animal {
  private String breed;
}

@Entity
@DiscriminatorValue("Cat")
public class Cat extends Animal {
  private String color;
}
```

The resulting table will store all fields for `Animal`, `Dog`, and `Cat` with a `ANIMAL_TYPE` column to identify the
type of entity (`Dog`, `Cat`, etc.).

---

### 4.2 Table per Concrete Class

- Each concrete class has its **own table**.
- Tables include all fields, inherited or not.
- Disadvantages:
    - Limited support for polymorphic associations.
    - Queries require SQL `UNION` or multiple database queries.

**Example:**

```java

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment {
  @Id
  @GeneratedValue
  private Long id;

  private double amount;
}

@Entity
public class CreditCardPayment extends Payment {
  private String cardNumber;
}

@Entity
public class PayPalPayment extends Payment {
  private String email;
}
```

In this case:

- Tables `CreditCardPayment` and `PayPalPayment` are separate but store the shared `amount` field along with their
  specific fields.

---

### 4.3 Joined Subclass Strategy

- A single table for the root class is used.
- Subclasses have **separate tables** for subclass-specific fields.
- The primary key in subclass tables acts as a **foreign key** to the parent table.

**Example:**

```java

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
  @Id
  @GeneratedValue
  private Long id;

  private String owner;
}

@Entity
public class SavingsAccount extends Account {
  private double interestRate;
}

@Entity
public class CheckingAccount extends Account {
  private double overdraftLimit;
}
```

In this case:

- The `Account` table stores common fields (`id`, `owner`).
- Separate tables for `SavingsAccount` and `CheckingAccount` hold subclass-specific data.

---

## Summary of Mapping Strategies

| Strategy                     | Advantages                              | Disadvantages                             |
|------------------------------|-----------------------------------------|-------------------------------------------|
| **Single Table**             | Good polymorphic support, fewer joins   | Subclass-specific fields must be nullable |
| **Table per Concrete Class** | Simple, no nullable fields              | Poor polymorphic query support            |
| **Joined Subclass**          | No nullable fields, better organization | Slower queries due to joins               |