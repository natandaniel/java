# A simple project with Spring Data JDBC showcasing some of its available features

The reference Spring Data JDBC documentation can be
found <a href="https://docs.spring.io/spring-data/relational/reference/jdbc.html">here</a>.

In this project, I model a single coffee house company that sells coffee beans, coffee drinks and coffee makers.

I have implemented corresponding domain classes and their repositories as well as integration tests using a Docker
contained
MySQL database.

This readme file contains quick notes of the Spring Data JDBC reference documentation as well as notes of specific
configuration I have used for my project.

### Spring Data JDBC motivation

The reference project for relational database connectivity is Spring Data JPA.
Spring Data JDBC provides a more simple framework with some limitations.

#### Design decisions

<ul>
<li>no lazy loading or caching</li>
<li>no dirty tracking or session</li>
<li>simple model for entity/table mapping</li>
</ul>

#### Domain driven design and relational databases

For Spring Data in general, there is one *Repository* per aggregate root.

For Spring Data JDBC, all entities reachable from an aggregate root are considered to be part of that aggregate root.
It is assumed that only the aggregate root has a foreign key to a table storing non-root entities of the aggregate. No
other entity points toward non-root entities.

### Getting started

#### Requirements

Spring Framework 6.2.2 and above.

Spring Data JDBC includes direct support for the following databases:
<ul>
<li>DB2</li>
<li>H2</li>
<li>HSQLDB</li>
<li>MariaDB</li>
<li>Microsoft SQL Server</li>
<li>MySQL</li>
<li>Oracle</li>
<li>Postgres</li>
</ul>

For non-supported databases, a vendor specific *Dialect* implementation is required.

#### Configuration

Annotate a configuration class with *@EnableJdbcRepositories* so that Spring provides implementations for interfaces
extending Spring Data *Repository* interfaces.

The configuration class extends Spring Data JDBC's *AbstractJdbcConfiguration*.

Configure a *DataSource* bean.

Configure a Spring JDBC *NamedParameterJdbcOperations* bean.

Configure a Spring JDBC *TransactionManager* bean.

If using Spring Boot with the *spring-boot-starter-data-jdbc* dependency, only a *DataSource* bean needs to be
configured, other beans are automatically included.

### Persisting entities

Create or update an entity with *CrudRepository.save(…)*.

Set *@Id* annotation on the identifier of an entity.

Some downsides:
<ul>
<li>if the aggregate root is not new, all referenced entities get deleted, the aggregate root gets updated, and all referenced entities get inserted again</li>
</ul>

Loading aggregates:
<ol>
<li>Traditional way: each query loads the aggregate roots, referenced entities are loaded separately</li>
<li>Since Spring Data JDBC 3.2: *Single Query Loading* (experimental), an arbitrary number of aggregates can be fully loaded with a single SQL query; this approach has requirements:
<ul>
<li>the aggregate must not have nested collections, including *Map*</li>
<li>the aggregate must not use AggregateReference or embedded entities</li>
<li>the database dialect must support it; H2 and HSQL don't support this</li>
<li>works only for find methods in CrudRepository, not for derived queries and annotated queries</li>
<li>needs to be enabled in JdbcMappingContext, by calling setSingleQueryLoadingEnabled(true)</li>
</ul>
</li>
</ol>


An alternative for querying and persisting is using the Spring bean *JdbcAggregateTemplate*.

### Mapping

*MappingJdbcConverter* provides mapping support.

Mapping instructions are understood explicitly through annotations or implicitly through conventions.

Conventions:
<ul>
<li>the int Java class name is mapped to the table name</li>
<li>field names are mapped this way: myFieldName -> my_field_name</li>
<li>table and column names derived this way are used without quotes in SQL statements</li>
<li>register converters with CustomConversions to override the default mapping of object properties to row columns and values</li>
</ul>

By default, the name of the foreign key column is the table name of the referencing entity.