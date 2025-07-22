# Use a base image with Java 21
FROM openjdk:21-jdk-slim

# Set a working directory
WORKDIR /app

# Copy the packaged application to the container
COPY target/springboot-api-uml-case-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","app.jar"]