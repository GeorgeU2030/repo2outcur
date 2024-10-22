FROM amazoncorretto:17-alpine-jdk
#COPY ./outcome-curr-mgmt/target/*.jar app.jar
WORKDIR /app
COPY app.jar .
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "app.jar"]



