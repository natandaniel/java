# Managing Entities with EntityManager

## Table of Contents

1. [Introduction](#1-introduction)
2. [EntityManager Interface](#2-entitymanager-interface)  
   2.1 [Overview of Entity States](#21-overview-of-entity-states)  
   2.2 [Container-Managed EntityManagers](#22-container-managed-entitymanagers)  
   2.3 [Application-Managed EntityManagers](#23-application-managed-entitymanagers)
3. [Entity Lifecycle Operations](#3-entity-lifecycle-operations)  
   3.1 [Persisting Entities](#31-persisting-entities)  
   3.2 [Finding Entities by Primary Key](#32-finding-entities-by-primary-key)  
   3.3 [Updating Entities (Merge)](#33-updating-entities-merge)  
   3.4 [Removing Entities](#34-removing-entities)  
   3.5 [Using Transactions](#35-using-transactions)
4. [Synchronization, Flushing, and Clearing](#4-synchronization-flushing-and-clearing)  
   4.1 [Flushing Changes to the Database](#41-flushing-changes-to-the-database)  
   4.2 [Clearing the Persistence Context](#42-clearing-the-persistence-context)
5. [Persistence Units](#5-persistence-units)  
   5.1 [Key Elements of `persistence.xml`](#51-key-elements-of-persistencexml)  
   5.2 [Packaging Persistence Units](#52-packaging-persistence-units)
6. [Summary](#6-summary)

## 1. Introduction

The **EntityManager** in Java EE provides APIs for the persistence of Java objects (Entities). It works within a *
*persistence context**, which manages the lifecycle of entities. Entity instances interact with the database via the
EntityManager and can exist in one of four states: **New**, **Managed**, **Detached**, and **Removed**.

## 2. EntityManager Interface

The **EntityManager** interface provides methods to interact with entities and their lifecycle. This section explains
how EntityManager operates in different modes.

### 2.1 Overview of Entity States

1. **New**: Instance is created but not persisted yet.
2. **Managed**: Entity is associated with an active persistence context, and changes to the entity are automatically
   synchronized with the database.
3. **Detached**: Entity exists but is no longer associated with any active persistence context.
4. **Removed**: Entity is scheduled for deletion from the database.

### 2.2 Container-Managed EntityManagers

1. Persistence context is automatically propagated in a JTA (Java Transaction API) transaction.
2. Lifecycle management is handled by the container.
3. Inject `EntityManager` directly with `@PersistenceContext`.

### 2.3 Application-Managed EntityManagers

1. Lifecycle of the `EntityManager` and its persistence context must be handled manually.
2. Each EntityManager instance creates its own persistence context.
3. Use `EntityManagerFactory` for creation and management.

## 3. Entity Lifecycle Operations

### 3.1 Persisting Entities

Use the `persist()` method to make an entity **managed** and add it to the database.

### 3.2 Finding Entities by Primary Key

Retrieve an entity by its primary key using the `find()` method. If the entity is in a detached state or doesn’t exist,
null is returned.

### 3.3 Updating Entities (Merge)

Use the `merge()` method to copy changes from a detached entity to the persistence context.

### 3.4 Removing Entities

Use the `remove()` method to mark an entity for deletion during the next transaction synchronization.

### 3.5 Using Transactions

Operations such as `persist()`, `merge()`, and `remove()` require an active transaction to succeed. The `EntityManager`
provides methods to begin, commit, and roll back transactions manually (in the case of application-managed
transactions).

## 4. Synchronization, Flushing, and Clearing

In this section, we discuss how to manually control the synchronization of the persistence context with the database and
manage memory by detaching entities.

### 4.1 Flushing Changes to the Database

The `flush()` method forces the persistence context to synchronize with the database. This ensures that all pending
changes (inserts, updates, deletions) are executed.

Example:

```java
em.flush();
```

### 4.2 Clearing the Persistence Context

The `clear()` method detaches all entities from the persistence context, leaving them in a **detached state**. This can
be useful in scenarios such as:

1. **Managing Memory**: Clearing the persistence context reduces memory usage when working with a large number of
   managed entities.
2. **Long-running Transactions**: Helps prevent excessive changes to entities from being stored in the persistence
   context, which can lead to performance degradation.

#### Example: Clearing the Persistence Context

```java
em.clear();
```

#### Key Notes:

- After calling `clear()`, all entities in the persistence context become **detached**.
- Changes to detached entities will no longer be tracked or synchronized with the database unless they are explicitly
  merged back into the persistence context using `merge()`.
- Useful for batch processing or resetting the state of the persistence context between transactions.

#### Use Case Scenario:

Suppose you're processing thousands of entities and persist new changes in a batch. After each batch, calling `clear()`
ensures the persistence context doesn't grow unnecessarily, which could cause memory issues.

Example:

```java
for(int i = 0; i <entities.

size();

i++){
    em.

persist(entities.get(i));

    // Commit and clear every 100 entities
    if(i %100==0){
    em.

flush();
      em.

clear();
    }
        }
```

## 5. Persistence Units

A **persistence unit** defines all entities managed by the persistence layer and is configured in the `persistence.xml`
file.

### 5.1 Key Elements of `persistence.xml`

- **persistence-unit name**: Unique identifier for the unit.
- **classes**: Lists the explicitly managed entity classes.
- **properties**: Database-related configuration parameters.

### 5.2 Packaging Persistence Units

- Persistence units can be packaged as part of a deployable archive, e.g., `WAR` or `EAR`.

## 6. Summary

- Use `persist()`, `find()`, `merge()`, and `remove()` to manage entity lifecycles.
- Use transactions (`begin()`, `commit()`, `rollback()`) for consistent data changes.
- Use `flush()` to synchronize changes with the database manually.
- Use `clear()` to detach entities and reset the persistence context in memory-intensive operations or long-running
  transactions.