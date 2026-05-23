FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/projedata-0.0.1-SNAPSHOT.jar /app/projedata.jar

EXPOSE 8080

CMD [ "java", "-jar", "projedata.jar" ]