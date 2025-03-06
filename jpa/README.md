# Jakarta Persistence API (JPA) Project

This project demonstrates key concepts of the **Jakarta Persistence API (JPA)**, allowing smooth interaction between
Java applications and relational databases.

## Features

- Map Java objects to relational database tables.
- Perform CRUD (`Create`, `Read`, `Update`, `Delete`) operations using the `EntityManager`.
- Define relationships between entities (e.g., `@OneToMany`, `@ManyToOne`).
- Configure persistence with PostgreSQL for development and production.
- Use **H2**, an in-memory database, for integration tests.

## Prerequisites

Before starting, make sure you have the following installed:

- **JDK 17** or higher.
- **Docker** to set up PostgreSQL.
- An IDE such as **IntelliJ IDEA** for running and editing the code.

## Setup and Configuration

1. **Clone the repository** to your local environment:

   ```bash
   git clone <REPOSITORY_URL>
   cd <PROJECT_NAME>
   ```

2. **Start a PostgreSQL container** using Docker:

   ```bash
   docker run --name jpa-tests-postgres \
       -e POSTGRES_USER=admin \
       -e POSTGRES_PASSWORD=secret \
       -e POSTGRES_DB=test \
       -p 5432:5432 \
       -d postgres:latest
   ```

3. Import the project into your IDE and run the provided example code to explore JPA features.

## Documentation

Discover the following resources to understand and use the project effectively:

1. [Entity Mapping](docs/1-entity.md)
2. [Entity Inheritance](docs/2-entity-inheritance.md)
3. [Validating Persistent Fields And Properties](docs/3-validating-persistent-fields-and-properties.md)
4. [Managing Entities](docs/4-managing-entities.md)
5. [Persistence XML file](docs/5-persistence-xml-file.md)

## Testing

The project includes both:

- **Development database**: PostgreSQL (via Docker) for application runtime.
- **Integration tests**: H2 is configured as an in-memory database for faster and isolated testing.
