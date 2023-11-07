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
DB_USERNAME
DB_PASSWORD
DB_HOST
DB_NAME
```

How to start the moves-like-swagger-api application
---

1. Run `mvn clean install -DskipTests=true` to build your application
1. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`

Tests
---
