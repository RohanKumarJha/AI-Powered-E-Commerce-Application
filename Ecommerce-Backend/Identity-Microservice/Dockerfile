FROM eclipse-temurin:17

WORKDIR /app

COPY target/Identity-Microservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]