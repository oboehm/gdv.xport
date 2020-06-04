# Packaging the gdv-xport-service.war
FROM maven:3.6.3-openjdk-8 AS build-env
COPY . .
RUN mvn package

# Starting the service
FROM openjdk:8-alpine
WORKDIR /usr/src/myapp
COPY --from=build-env /service/target/gdv-xport-service-4.0.4.war /usr/src/myapp/app.war
EXPOSE 2517
ENTRYPOINT ["java", "-jar", "app.war"]
