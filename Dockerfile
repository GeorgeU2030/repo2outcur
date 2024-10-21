FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY outcome-curr-mgmt/target/*.jar app.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "app.jar"]
