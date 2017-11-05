FROM openjdk:8-jre-alpine
COPY build/libs/gs-spring-boot-docker-0.1.0.jar /app.jar
ENTRYPOINT [ "java" ,"-jar","-Djava.security.egd=file:/dev/./urandom", "/app.jar" ]