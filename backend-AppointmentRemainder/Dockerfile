# Use a base image with OpenJDK to run Java applications
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application (JAR file) into the container
COPY target/reminder.war /app/reminder.war

# Expose the port that the Spring Boot application uses (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "reminder.war"]
