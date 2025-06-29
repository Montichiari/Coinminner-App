FROM eclipse-temurin:17-jre

EXPOSE 8080 8081

RUN mkdir app

COPY target/myapp-0.0.1-SNAPSHOT.jar app
COPY config.yml app

WORKDIR app

CMD ["java","-jar","myapp-0.0.1-SNAPSHOT.jar","server","config.yml"]