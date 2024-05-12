# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Copy the built JAR file from the build stage
COPY --from=build /target/*.jar app.jar

# Expose the port that your application listens on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]