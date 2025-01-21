## Introduction

<a href="https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/">The JDBC API</a> (Java Database
Connectivity) is a low-level Java API enabling the access to and management of data stored in relational databases.

It allows Java programs to:
<ul>
<li>connect to a relational database</li>
<li>execute SQL statements</li>
<li>retrieve and process the results received from the database in response to a SQL statement</li>
</ul>


The Spring Framework provides JDBC support classes **JdbcTemplate**, **NamedParameterJdbcTemplate** and **JdbcClient**
that
build on top of the JDBC API's main database connectivity interface, **DataSource**.

In this module, I experiment the Spring Framework's support for data access with JDBC.

I have declared the following artifacts as dependencies:
<ul>
<li>spring-context: to use Spring's IoC container for Dependency Injection</li>
<li>spring-jdbc: to use Spring's abstraction of the JDBC API</li>
<li>mysql-connector-j: to be able to use the JDBC API with the MySQL DBMS (Database Management System)</li>
<li>junit-jupiter: to run JUnit 5 tests</li>
<li>spring-test: to get a configured TestContext in my JUnit 5 tests</li>
</ul>

I also use the sample data provided
in <a href="https://docs.oracle.com/javase/tutorial/jdbc/basics/gettingstarted.html">The Java Tutorials JDBC trail</a>
to create tables and populate them.