## Introduction

In this module, I experiment with the Spring Framework's support for data access
with <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/">JDBC</a> (Java Database Connectivity).

The used artifacts are:
<ul>
<li>spring-context: to use Spring's IoC container for Dependency Injection</li>
<li>spring-jdbc: to use Spring's client classes built on top of the JDBC API</li>
<li>mysql-connector-j: to be able to use the JDBC API with the MySQL DBMS (Database Management System)</li>
</ul>

I also use the sample data provided
in <a href="https://docs.oracle.com/javase/tutorial/jdbc/basics/gettingstarted.html">The Java Tutorials JDBC trail</a>
to create tables and populate them.

## Configuring the development environment

### Install the latest Java Development Kit

Get the latest release of the Java SE Platform development
kit for your operating system <a href="https://www.oracle.com/java/technologies/downloads/">here</a>.

Ensure that the full directory path of the JDK **bin** directory is in your **PATH** environment variable to be able to
run the
Java compiler and the Java application launcher from any directory.

### Install Docker

I use Docker to easily run and access a MySQL DBMS server.

Install the Docker Engine for your operating system <a href="https://docs.docker.com/engine/install/">here</a>.

### Install the latest MySQL DBMS with Docker

Open your terminal and run the following command:

    docker run --name mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=mysql_test_db -p 3306:3306 -d mysql:latest

This command installs the latest MySQL server in a container named "mysql", exposes the server on port 3306, creates a
database named "mysql_test_db" and sets the password for user "root" to "admin". 
