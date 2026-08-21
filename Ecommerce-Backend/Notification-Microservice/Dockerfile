FROM eclipse-temurin
WORKDIR /app
COPY target/Notification-Microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","app.jar"]