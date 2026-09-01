FROM eclipse-temurin:17

WORKDIR /app

COPY target/Order-Microservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]