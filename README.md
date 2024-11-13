# Student Course Enrollment System

This project demonstrates a **Student Course Enrollment System** that models the relationship between students and their unique ID cards. Built with **Spring Data JPA** and **PostgreSQL**, it showcases a one-to-one entity relationship between `Student` and `StudentIdCard`.

## Overview

This system is designed for efficient interaction with the database, ensuring data consistency and optimized performance through lazy fetching and cascading operations. In this documentation, we explain the key **JPA annotations** used to map Java classes to database tables, define relationships, and configure cascading and fetching strategies.

We also cover the essential database configurations needed to connect to PostgreSQL, including database URLs, user credentials, and Hibernate settings for schema management.

---

## Entity Annotations Explained

### 1. `@Entity` and `@Table`
- `@Entity`: Marks the class as a JPA entity, mapping it to a table in the database.
- `@Table(name = "table_name")`: Specifies the name of the table in the database.

### 2. `@Id` and `@GeneratedValue`
- `@Id`: Marks a field as the primary key for the entity.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Configures auto-incrementing behavior for the primary key, where the database generates a new unique key for each new entry.

### 3. `@Column`
```java
@Column(name = "column_name", nullable = false, unique = true, columnDefinition = "TEXT")
```
- `name`: Specifies the column name in the database.
- `nullable`: Defines whether the column can be `NULL` (`false` indicates it is required).
- `unique`: Ensures no two entries in the table can have the same value for this column.
- `columnDefinition = "TEXT"`: Forces the use of the `TEXT` type in PostgreSQL instead of the default `VARCHAR` for string fields.

### 4. `@OneToOne` and `@JoinColumn`

- `@OneToOne`: Establishes a one-to-one relationship between two entities, linking each `Student` with a unique `StudentIdCard`.
- `@JoinColumn(name = "foreign_key_column")`: Defines the name of the foreign key column in the owning entity (e.g., `student_id` in `StudentIdCard`).

### 5. CascadeType and FetchType

- **CascadeType**: Specifies which operations should cascade from a parent entity to its associated entities.
    - `CascadeType.ALL`: All operations (persist, merge, remove, refresh, detach) performed on the parent entity (`Student`) will also apply to its associated `StudentIdCard`.
    - **Use case**: By setting `CascadeType.ALL` on `Student` for the `studentIdCard` relationship, any changes to the `Student` entity (like saving or deleting) will automatically apply to the associated `StudentIdCard`.

- **FetchType**: Defines how data should be fetched for associated entities.
    - `FetchType.LAZY`: Loads associated entities only when they’re accessed for the first time, saving resources when working with large datasets.
    - `FetchType.EAGER`: Loads associated entities immediately along with the parent entity.
    - **Use case**: Setting `FetchType.LAZY` on the `studentIdCard` relationship in `Student` ensures that `StudentIdCard` data is loaded only when explicitly accessed, which can improve performance.

### 6. `@Repository`

- `@Repository`: Defines a Data Access Object (DAO) in Spring, handling CRUD (Create, Read, Update, Delete) operations for an entity. It enables exception translation, converting low-level database exceptions into Spring’s `DataAccessException`.

---

## Database Configuration

The application is configured to connect to a PostgreSQL database with the following properties:
```properties
spring.application.name=Student Course Enrollment System
spring.datasource.url=jdbc:postgresql://localhost:5432/****
spring.datasource.username=****
spring.datasource.password=****
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```
### Explanation of Each Property

- `spring.application.name`: Sets the application name, useful in logs.
- `spring.datasource.url`: Specifies the URL for the PostgreSQL database connection.
- `spring.datasource.username` and `spring.datasource.password`: Set the database username and password.
- `spring.jpa.hibernate.ddl-auto=update`: Configures Hibernate to automatically update the database schema based on the entities.
- `spring.jpa.show-sql=true`: Enables SQL logging in the console for debugging.
- `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect`: Instructs Hibernate to optimize SQL generation for PostgreSQL.
- `spring.jpa.properties.hibernate.format_sql=true`: Formats the SQL output for readability in console logs.

---

This configuration ensures smooth integration between the application and a PostgreSQL database, allowing JPA to manage entity relationships, perform cascading operations, and optimize resource usage with fetch strategies.

This completes the core setup for a well-configured **Student Course Enrollment System** using Spring Data JPA and PostgreSQL.

