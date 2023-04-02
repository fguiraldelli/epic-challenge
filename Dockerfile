FROM eclipse-temurin:17-alpine

LABEL maintainer="francisco.guiraldelli@gmail.com"

WORKDIR /app

COPY target/epic-challenge-0.0.1-SNAPSHOT.jar /app/epic-challenge.jar

ENTRYPOINT ["java", "-jar", "epic-challenge.jar"]