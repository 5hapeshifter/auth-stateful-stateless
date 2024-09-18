FROM openjdk:17-alpine
COPY target/statefull-auth-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]