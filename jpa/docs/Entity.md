
<!-- TOC -->
* [Jakarta Persistence API Entities](#jakarta-persistence-api-entities)
  * [1. Definition](#1-definition)
  * [2. Requirements for Entity Classes](#2-requirements-for-entity-classes)
  * [3. Persistent Fields and Properties in Entity Classes](#3-persistent-fields-and-properties-in-entity-classes)
    * [3.1 Supported Persistent Types](#31-supported-persistent-types)
    * [3.2 Persistent Fields](#32-persistent-fields)
    * [3.3 Persistent Properties](#33-persistent-properties)
    * [3.4 Collection-Valued Fields and Properties](#34-collection-valued-fields-and-properties)
    * [3.5 Map Collections](#35-map-collections)
    * [3.6 Relationships](#36-relationships)
    * [3.7 Key Considerations](#37-key-considerations)
  * [4. Primary Keys](#4-primary-keys)
    * [4.1 Simple Primary Keys](#41-simple-primary-keys)
    * [4.2 Composite Primary Keys](#42-composite-primary-keys)
  * [5. Multiplicity in Entity Relationships](#5-multiplicity-in-entity-relationships)
    * [5.1 One-to-One](#51-one-to-one)
    * [5.2 One-to-Many](#52-one-to-many)
    * [5.3 Many-to-One](#53-many-to-one)
    * [5.4 Many-to-Many](#54-many-to-many)
  * [6. Direction in Entity Relationships](#6-direction-in-entity-relationships)
    * [6.1 Bidirectional Relationships](#61-bidirectional-relationships)
    * [6.2 Unidirectional Relationships](#62-unidirectional-relationships)
    * [6.3 Queries and Relationship Direction](#63-queries-and-relationship-direction)
    * [6.4 Cascade Operations](#64-cascade-operations)
    * [6.5 Orphan Removal](#65-orphan-removal)
  * [7. Embeddable Classes in Entities](#7-embeddable-classes-in-entities)
    * [7.1 Characteristics](#71-characteristics)
    * [7.2 Composition of Embeddable Classes](#72-composition-of-embeddable-classes)
    * [7.3 Usage](#73-usage)
<!-- TOC -->
# Jakarta Persistence API Entities

## 1. Definition

- a lightweight, persistent domain object that represents a table in a relational database
- the persistent state of an entity is represented through either persistent fields or persistent properties
- these fields or properties use object/relational mapping annotations to map the entities and entity relationships to
  the relational data in the underlying data store

## 2. Requirements for Entity Classes

- **Annotation**: Class must be annotated with `@jakarta.persistence.Entity`.
- **Constructor**: Must have a public or protected no-argument constructor; other constructors are allowed.
- **Final Declaration**: Class, methods, and persistent instance variables must not be declared `final`.
- **Serialization**: If instances are to be passed by value as detached objects (e.g., through a session bean’s remote
  business interface), the class must implement the `Serializable` interface.
- **Inheritance**: Entities may extend both entity and non-entity classes; non-entity classes may extend entity classes.
- **Access Modifiers**: Persistent instance variables should be declared private, protected, or package-private and can
  be accessed directly only by the entity class’s methods; clients must access the entity’s state through accessor or
  business methods.

## 3. Persistent Fields and Properties in Entity Classes

The persistent state of an entity is accessed through fields or properties. These must follow specific rules and fall under supported types.

### 3.1 Supported Persistent Types
- **Primitive types**: `int`, `double`, etc.
- **Wrapper types**: `Integer`, `Double`, etc.
- **Strings**: `java.lang.String`.
- **Date/Time types** (preferred):
    - `java.time.LocalDate`, `java.time.LocalTime`, `java.time.LocalDateTime`, `java.time.OffsetDateTime`.
    - **Deprecated**: `java.util.Date`, `java.util.Calendar`, `java.sql.Date/Time/Timestamp` (use `java.time` classes instead).
- **Serializable types**: `java.math.BigInteger`, `java.math.BigDecimal`, or custom serializable classes.
- **Character and Byte Arrays**: Restricted to `byte[]`, `Byte[]`, `char[]`, and `Character[]`.
- **Enumerated types**.
- **Relationships**: Other entities or collections of entities.
- **Embeddable classes**: Classes annotated with `@Embeddable`.

### 3.2 Persistent Fields
- **Field Access**: If annotations are placed on fields (instance variables), they are persisted directly, excluding those marked with `@Transient` or declared `transient`.

### 3.3 Persistent Properties
- **Property Access**: If annotations are on getter methods, properties must follow JavaBeans conventions (`getProperty`/`isProperty` for boolean getters, `setProperty` for setters). Properties marked with `@Transient` are excluded from persistence.

### 3.4 Collection-Valued Fields and Properties
- **Supported Collections**: JPA supports collections such as `Collection`, `Set`, `List`, and `Map`. These may contain either:
    - Basic types: Strings, numbers, or enumerated types.
    - Embeddable classes: Types annotated with `@Embeddable`.
    - Entities: For relationships, collections may contain other entities (see relationships below for additional details).

- **Element Collections**:
    - Collections of basic types or embeddable classes are stored in a **collection table** in the database, mapping these values to the parent entity using a foreign key.
    - Use `@ElementCollection` to specify these types.

- **Collections of Entities**:
    - Collections of entities represent standard JPA relationships (e.g., one-to-many or many-to-many).
    - If a `Map` is used, keys can be basic types, embeddable classes, or entities (see below for Map-specific annotations).

### 3.5 Map Collections
- **Key Types**:
    - Keys in a Map can be basic types, embeddable classes, or other entities.
- **Annotations for Keys**:
    - Use `@MapKeyColumn` to map keys of basic types (e.g., strings, integers).
    - Use `@MapKeyJoinColumn` for entity keys.
    - Use `@MapKeyJoinColumns` when composite keys involve multiple join columns.
    - Use `@MapKeyClass` to explicitly define the key class if generics are not used.
    - Use `@MapKey` when the key is a field or property of the target entity being mapped.
- **Mapping in the Database**:
    - Basic or embeddable values in Map fields are stored in **collection tables**, with the keys mapped to value columns.
    - When Map values are entities, **join tables** are used to connect the key and value entities via their foreign keys.

| Key Type         | Value Type              | Annotation                                         |
|------------------|-------------------------|----------------------------------------------------|
| Basic Type       | Basic Type / Embeddable | `@ElementCollection` + `@MapKeyColumn`             |
| Entity           | Basic Type / Embeddable | `@ElementCollection` + `@MapKeyJoinColumn`         |
| Basic Type       | Entity                  | `@OneToMany` / `@ManyToMany` + `@MapKeyColumn`     |
| Entity           | Entity                  | `@OneToMany` / `@ManyToMany` + `@MapKeyJoinColumn` |
| Persistent Field | Entity                  | `@OneToMany` / `@ManyToMany` + `@MapKey`           |
| No Generics      | Any                     | `@MapKeyClass`                                     |

### 3.6 Relationships
- **One-to-One**:
    - Uses a foreign key in one of the entity tables to map the relationship.
- **Many-to-One**:
    - The "many" side stores a foreign key referencing the associated "one" entity.
- **One-to-Many**:
    - By default, represented with a foreign key in the table of the target entity.
    - Alternatively, **unidirectional One-to-Many** relationships can use a join table to store the association.
- **Many-to-Many**:
    - Always represented using a **join table**, which maps foreign keys from both entities.
    - Can involve additional entity attributes in the join table to model more complex relationships.

### 3.7 Key Considerations
- Use **collection tables** for collections of basic or embeddable types. These tables store data as a list associated with the owning entity.
- Use **join tables** for many-to-many or unidirectional one-to-many relationships to pair entities through their foreign keys.

## 4. Primary Keys

Each entity must have a unique identifier known as the primary key, which enables clients to locate a specific entity
instance. A primary key can be **simple** or **composite**:

### 4.1 Simple Primary Keys

- Defined with the `jakarta.persistence.Id` annotation.
- Must be one of the following types:
    - Java primitive types or their wrappers
    - `java.lang.String`
    - `java.time.LocalDate`
    - `java.math.BigDecimal`
    - `java.math.BigInteger`
- Avoid using floating-point types.
- Generated primary keys should primarily use integral types for portability.

### 4.2 Composite Primary Keys

- Consist of more than one attribute.
- Defined using `jakarta.persistence.EmbeddedId` or `jakarta.persistence.IdClass` annotations.
- Represented by a primary key class which:
    - Must be public.
    - Must have a public default constructor.
    - Must implement `hashCode()` and `equals(Object other)`.
    - Must be serializable.
    - Properties must be public or protected if property-based access is used.
- Mapped either to fields or properties of the entity class or as an embeddable class.
- The names and types of the primary key properties in the primary key class must match those in the entity class.

## 5. Multiplicity in Entity Relationships

Entity relationships define how entities relate to one another, with the following types:

### 5.1 One-to-One

- **Definition**: Each entity instance is associated with a single instance of another entity.
- **Annotation**: `@jakarta.persistence.OneToOne`

### 5.2 One-to-Many

- **Definition**: An entity instance is related to multiple instances of another entity.
- **Annotation**: `@jakarta.persistence.OneToMany`

### 5.3 Many-to-One

- **Definition**: Multiple entity instances are related to a single instance of another entity.
- **Annotation**: `@jakarta.persistence.ManyToOne`

### 5.4 Many-to-Many

- **Definition**: Entity instances are related to multiple instances of each other.
- **Annotation**: `@jakarta.persistence.ManyToMany`

## 6. Direction in Entity Relationships

Relationships between entities can be **bidirectional** or **unidirectional**.

### 6.1 Bidirectional Relationships

- **Definition**: Both entities have relationship fields or properties referring to each other.
- **Owning Side**:
    - The owning side controls the relationship and is responsible for database updates.
    - The owning side is determined by the foreign key in the database.
- **Rules**:
    - The inverse side must reference the owning side using the `mappedBy` element in `@OneToOne`, `@OneToMany`, or
      `@ManyToMany`.
    - For `@ManyToOne` relationships, the many side is always the owning side.
    - For `@ManyToMany` relationships, either side can be the owning side.

### 6.2 Unidirectional Relationships

- **Definition**: Only one entity has a relationship field or property referring to the other entity.

### 6.3 Queries and Relationship Direction

- Query navigation depends on the relationship direction:
    - **Unidirectional**: Queries can navigate in a single direction.
    - **Bidirectional**: Queries can navigate in both directions.

### 6.4 Cascade Operations

- **Definition**: Ensures dependent entities are updated or deleted with a parent entity.
- **Types (`jakarta.persistence.CascadeType`)**:
    - `ALL`: Applies all cascade operations (`DETACH`, `MERGE`, `PERSIST`, `REFRESH`, `REMOVE`).
    - `DETACH`: Detaches the related entity when the parent is detached.
    - `MERGE`: Merges the related entity when the parent is merged.
    - `PERSIST`: Persists the related entity when the parent is persisted.
    - `REFRESH`: Refreshes the related entity when the parent is refreshed.
    - `REMOVE`: Removes the related entity when the parent is removed.
- Cascading is specified using the `cascade` element in relationship annotations (e.g., `@OneToMany(cascade=REMOVE)`).

### 6.5 Orphan Removal

- **Definition**: Automatically removes entities that are no longer referenced.
- **Usage**:
    - Enabled with `orphanRemoval=true` in `@OneToOne` or `@OneToMany` relationships.
    - Ensures "orphaned" entities (disconnected from their parent) are deleted.
- Default value: `false`

## 7. Embeddable Classes in Entities

Embeddable classes are used to represent the state of an entity but do not have a persistent identity of their own. They
share the identity of the entity that owns them and exist only as part of the owning entity's state.

### 7.1 Characteristics

- **Annotation**: Defined with `@jakarta.persistence.Embeddable` instead of `@Entity`.
- **Ownership**: Entities owning embeddable classes may use the `@jakarta.persistence.Embedded` annotation for embedding
  fields or properties, but this is optional.
- **Rules**: They follow the same rules as entity classes.

### 7.2 Composition of Embeddable Classes

- Can use other embeddable classes to represent their state.
- Can contain collections of basic types or other embeddable classes.
- Can have relationships to other entities or collections of entities.
    - In such cases, the relationship is from the target entity (or collection) to the owning entity.

### 7.3 Usage

- Embeddable classes represent reusable and structured parts of an entity's state, simplifying the representation of
  complex attributes or composite fields in entities.