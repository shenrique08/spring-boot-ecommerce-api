# Stage 1: Build the application using Maven
FROM openjdk:21-jdk-slim AS build

WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies (this will be cached for faster builds)
RUN ./mvnw dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN ./mvnw package -DskipTests

# Stage 2: Create the final, smaller image
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the packaged application from the build stage
COPY --from=build /app/target/springboot-api-uml-case-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","app.jar"]