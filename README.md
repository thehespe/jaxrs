Task:

1. Use Liquibase to create database model with two tables with text data and picture data.
2.  Design API for two tables using JAX-RS and Swagger (creating, updating and getting data)

Used technolgies:

- PostgreSQL,
- Liquibase,
- Java 8,
- JAX-RS with Jersey 2,
- Swagger 1.5,
- Tomcat 9.

How to use:

Before first deploying application, updating liquibase.properties file with correct data.
Next step is updating liquibase by typing command "mvn liquibase:update" in main project directory.
Next, you can deploying war file on server application, eg. Tomcat.
To see all rest services with descriptions you can use Swagger by typing following url: http://localhost:8080/jaxrs/index.html (in case of local deployment).
