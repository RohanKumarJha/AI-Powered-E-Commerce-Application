FROM eclipse-temurin
WORKDIR /app
COPY target/Payment-Microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","app.jar"]