FROM openjdk:11-slim

ADD target/evil-project-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "evil-project-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
