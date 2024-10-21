FROM amazoncorretto:17-alpine-jdk
#COPY outcome-curr-mgmt/target/*.jar app.jar
#EXPOSE 9092
#ENTRYPOINT ["java", "-jar", "app.jar"]

RUN mkdir /app
WORKDIR /app/
ADD outcome-curr-mgmt-1.0-SNAPSHOT.jar /app/

EXPOSE 9092

CMD ["java", "-jar", "outcome-curr-mgmt-1.0-SNAPSHOT.jar"]

