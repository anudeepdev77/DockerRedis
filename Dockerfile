# Use an OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Copy the jar file into the container
COPY target/RedisDemo1-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
#EXPOSE 8000

RUN apt-get update && apt-get install -y iputils-ping

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]