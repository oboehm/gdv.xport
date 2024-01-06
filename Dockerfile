# Packaging the gdv-xport-service.war
FROM maven:3.6.3-openjdk-17-slim AS build-env
COPY . .
RUN mvn -DskipTests package

# Starting the service
FROM bellsoft/liberica-openjdk-alpine-musl:17
WORKDIR /usr/src/myapp
COPY --from=build-env /service/target/gdv-xport-service.war /usr/src/myapp/app.war
EXPOSE 2517
ENTRYPOINT ["java", "-jar", "app.war"]
