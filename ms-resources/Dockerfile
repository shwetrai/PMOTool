FROM openjdk:8-jdk-alpine
MAINTAINER ppant
RUN mkdir /app
RUN mkdir /pmologs
WORKDIR /app
COPY /target/ms-resources-0.0.1-SNAPSHOT.jar resourceapi.jar
COPY /target/classes/application-dev.properties application-dev.properties
CMD java -jar resourceapi.jar
EXPOSE 8083