FROM openjdk:11-jre-slim
WORKDIR /app
COPY /target/TargSoftTestTask*.jar /app/TargSoftTestTask.jar
ENTRYPOINT ["java", "-jar", "TargSoftTestTask.jar"]