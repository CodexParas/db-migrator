# DB Migrator

This project is a DB Migrator that facilitates migrating data between different databases (MySQL, PostgreSQL, Oracle).
It leverages Spring Boot with Spring Batch for executing database migrations and inserting demo data into various
databases. It exposes REST APIs for migration operations and data insertion.

## Features

- Migrate data between MySQL, PostgreSQL, and Oracle databases.
- Insert demo data into different databases.
- Configurable using Spring Batch jobs.
- OpenAPI documentation for API endpoints.

## Requirements

- Java 21
- Maven
- Docker (Optional, for running database containers)
- Databases: MySQL, PostgreSQL, Oracle (must be set up and running for migration)

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/CodexParas/db-migrator.git
cd db-migrator
```

### 2. Install dependencies

Make sure you have Maven installed and run the following to install the project dependencies:

```bash
mvn clean install
```

### 3. Running the databases with Docker (Optional)

You can use the provided `docker-compose.yml` to set up database containers. Simply run:

```bash
docker-compose up -d
```

### 4. Configure your databases

In the `src/main/resources/application.yaml`, configure the database connection settings for your MySQL, PostgreSQL, and
Oracle instances.

### 5. Running the application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

## Batch Jobs

The application uses **Spring Batch** to handle the migration and data insertion operations. Batch jobs allow for
efficient and transactional processing of large data sets.

### Migration Jobs

There are several migration jobs configured to move data between different databases. These jobs can be executed through
the REST API endpoints or can be triggered directly in the application.

- **Migrate to MySQL**: Migrates data from other databases to MySQL.
- **Migrate to Oracle**: Migrates data from other databases to Oracle.
- **Migrate to PostgreSQL**: Migrates data from other databases to PostgreSQL.

The batch jobs run in the background to ensure efficient and error-free migration, with automatic transaction
management.

### Data Insertion Jobs

Data insertion jobs are configured to populate demo data into the respective databases. These jobs are triggered by the
respective `/data/insert` API endpoints. The job will generate the requested number of records and insert them into the
chosen database.

### Batch Job Configuration

Batch jobs are configured in the `src/main/java/com/paras/db_migrator/config/job` package. Each migration and data
insertion job is configured with:

- **Job Parameters**: Parameters like database source, target, and record count.
- **Job Steps**: Each job is divided into smaller steps (e.g., reading data, processing data, writing data).
- **Job Listeners**: Listeners are used to capture job execution events (e.g., start, end, or failure).

You can modify the batch job configurations in the corresponding files within the `config/job` folder to suit your
needs.

## REST APIs

The OpenAPI specification is available for viewing and interacting with the APIs. You can access it at:

```
http://localhost:9121/api/swagger-ui.html
```

This UI provides detailed documentation for all the available endpoints and allows you to test the APIs directly.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.