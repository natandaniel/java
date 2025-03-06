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

---

## 2. Mapped Superclasses

- Declared with `@MappedSuperclass`.
- Used to share common state and mapping information without being an entity.
- **Characteristics**:
    - Cannot be queried or used in `EntityManager` operations.
    - Cannot be targets of entity relationships.
    - Do not have corresponding tables in the database.
    - Subclasses define the table mappings.
    - Can be **abstract or concrete**.

---

## 3. Non-Entity Superclasses

- Superclasses that are not annotated as entities.
- **Characteristics**:
    - Inherited state is **non-persistent**.
    - Cannot be used in `EntityManager` or `Query` operations.
    - Mapping or relationship annotations are ignored.

---

## 4. Entity Inheritance Mapping Strategies

Determines how the Java Persistence provider maps inherited entities in the datastore. Configure it by adding
`@Inheritance` to the root class of the hierarchy. Available strategies:

### 4.1 Single Table per Class Hierarchy (Default)

- A **single table** for all classes in the hierarchy.
- **Discriminator column**:
    - Configured with `@DiscriminatorColumn`.
    - Default name: `DTYPE`.
    - Default type: `DiscriminatorType.STRING`.
- **Discriminator values**:
    - Can be set with `@DiscriminatorValue`.
    - Default (if not specified):
        - For `STRING`: the entity name.
- Advantages:
    - Good support for **polymorphic relationships** and queries.
- Disadvantages:
    - Requires nullable columns for subclass-specific fields.

---

### 4.2 Table per Concrete Class

- Each concrete class has a **separate table**.
- Tables include all fields, including inherited ones.
- Disadvantages:
    - Poor support for polymorphic relationships.
    - Queries require SQL `UNION` or separate SQL queries.
    - Optional JPA feature, may not be supported by all providers.

---

### 4.3 Joined Subclass Strategy

- Root class has a **single table**.
- Subclasses have **separate tables** for subclass-specific fields.
- Primary key in subclass tables acts as a **foreign key** to the parent table.
- Advantages:
    - Good support for **polymorphic relationships**.
- Disadvantages:
    - Queries require **join operations**, leading to slower performance for deep hierarchies.
    - Discriminator column may be required in the root table.

---

## Summary of Mapping Strategies

| Strategy                     | Advantages                              | Disadvantages                             |
|------------------------------|-----------------------------------------|-------------------------------------------|
| **Single Table**             | Good polymorphic support, fewer joins   | Subclass-specific fields must be nullable |
| **Table per Concrete Class** | Simple, no nullable fields              | Poor polymorphic query support            |
| **Joined Subclass**          | No nullable fields, better organization | Slower queries due to joins               |