##### Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -Dquarkus.package.type=uber-jar

##### Extract JAR Stage
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

##### Final Stage
COPY --from=builder /app/target/*-runner.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]