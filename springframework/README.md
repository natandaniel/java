## Configuring the development environment to run the samples in this module

### Install Maven

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
