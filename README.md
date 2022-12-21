# CLEVERTEC-TEST

Technology stack:
- Spring-boot
- Spring WEB
- Spring Data JPA
- Gradle 7.5
- Postgres
- Docker-compose
- Java 17
- Spring validation
- Liquibase
- SQL

Starting app:
- In file application.properties you need write your url for db
-  If you don't have docker, execute this command ```./gradlew build && java -jar build/libs/clevertec-0.0.1-SNAPSHOT.jar```
- If you have docker, execute this command ```docker-compose up```

Endpoints:
- http://localhost:8080/product - working with products. You can use all CRUD operations 
- http://localhost:8080/discount - working with discount card. You can use all CRUD operations
- http://localhost:8080/check?itemId=1&itemId=2&card= ```card name``` - working with check. Response in console
- http://localhost:8080/check/file?itemId=1&itemId=2&card= ```card name``` - working with check. Response in txt file