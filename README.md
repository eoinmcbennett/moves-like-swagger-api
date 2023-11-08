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
2. Make note of the following files where to set the enviroment variables: 

Environment variables in the `docker-compose.yml` file using the environment key under each service. For example:
```
services:
  api:
    container_name: example
    hostname: example
    environment:
      - DB_USERNAME=$DB_USERNAME
      - DB_PASSWORD=$DB_PASSWORD
      - DB_HOST=$DB_HOST
      - DB_NAME=$DB_NAME
```

Environment variables in the `Dockerfile` can be set using the ENV instruction. For example: 
```
ENV DB_HOST ${DB_HOST}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_NAME ${DB_NAME}
```

For the `docker.yml` and `maven.yml` files:  
```
env:
    DB_PASSWORD: ${{ secrets.DB_PASSWORD }}
    DB_USERNAME: ${{ secrets.DB_USERNAME }}
    DB_HOST: ${{ secrets.DB_HOST }}
    DB_NAME: ${{ secrets.DB_NAME }}
```

Environment variables in Java applications are often read using methods like System.getenv("ENV_VARIABLE_NAME") within your Java code. You can use these variables to configure your application. For example this can be included in the `DatabaseConnector.java` file: 
```
String user      = System.getenv("DB_USERNAME");
String password  = System.getenv("DB_PASSWORD");
String host      = System.getenv("DB_HOST");
String database  = System.getenv("DB_NAME");
```

How to start the moves-like-swagger-api application
---

1. Run `mvn clean install -DskipTests=true` to build your application
2. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`

Tests
---
