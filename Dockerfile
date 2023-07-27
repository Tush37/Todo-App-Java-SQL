# Use a base image with Java and Maven pre-installed
FROM maven:3.8.7-openjdk-18-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests=true

EXPOSE 8102

CMD ["java", "-jar", "target/restapi_todo_v1-0.0.1-SNAPSHOT.jar"]
