# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk17:alpine-jre

COPY --from=build /target/*.jar app.jar
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java","-jar","app.jar"]