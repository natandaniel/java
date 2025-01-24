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



