FROM eclipse-temurin:17

WORKDIR /app

COPY target/Eureka-Microservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]