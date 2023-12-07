FROM openjdk:17-jdk-alpine

ARG USERNAME
ARG PASSWORD
ENV MONGODB_USERNAME=${USERNAME}
ENV MONGODB_PASSWORD=${PASSWORD}
#EXPOSE 8081
COPY target/LoginModule-0.0.1-SNAPSHOT.jar Login.jar
ENTRYPOINT ["java","-jar","Login.jar"]

