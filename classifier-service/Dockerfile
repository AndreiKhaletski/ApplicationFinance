FROM openjdk:17-jdk
WORKDIR /app
COPY target/classifier-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]