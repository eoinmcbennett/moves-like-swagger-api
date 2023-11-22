# moves-like-swagger-api

Database
---
1. Run each SQL script in order. 
```
mysql --host=<localhost> --user=<your_username> --password=<your_password> <your_database_name> <SQL-SCRIPT>
```
git
Config
---
1. The following environment variables need to be set to enable database connection:
```
DB_USER
DB_PASSWORD
DB_HOST
DB_NAME
```

2. The following environment variables need to be set to allow authentication to work.
Make sure to set this to a long random string.
```
JWT_SECRET
```
3. Configure Docker when building a Docker image for the application.

This command configures your Docker image with the database connection parameters, making it ready for deployment.

Run the following command from the root directory: 
```
docker build --build-arg DB_HOST=${DB_HOST} --build-arg DB_PASSWORD=${DB_PASSWORD} --build-arg DB_USERNAME=${DB_USERNAME} --build-arg DB_NAME=${DB_NAME} -t myapp:0.1 .
```

How to start the moves-like-swagger-api application
---

1. Run `mvn clean install -DskipTests=true` to build your application
2. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

Swagger
---
To see your applications Swagger UI `http://localhost:8080/swagger`

Linting
---
In order to check your code with the linter locally. Run `mvn checkstyle:check -e` and address anything in the output

Tests
---
The following environment variables need to be set to perform integration tests of the login system.
Make sure these are set to valid login credentials
```
TEST_VALID_USERNAME
TEST_VALID_PASSWORD
```
1. Run `mvn clean test` to run all tests (ensure the application is ***not*** running before attempting to run tests)
