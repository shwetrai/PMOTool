FROM openjdk:8-jdk-alpine
MAINTAINER ppant
RUN mkdir /app
WORKDIR /app
COPY /target/ms-resources-0.0.1-SNAPSHOT.jar resourceapi.jar
COPY /target/classes/application.properties application-dev.properties
CMD java -jar resourceapi.jar --spring.config.location=file:/app/application-dev.properties
EXPOSE 8082