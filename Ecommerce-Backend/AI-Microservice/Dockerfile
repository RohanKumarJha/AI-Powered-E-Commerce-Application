FROM eclipse-temurin
WORKDIR /app
COPY target/AI-Microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","app.jar"]