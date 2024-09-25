FROM maven:3.9.9-amazoncorretto-17 AS builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM amazoncorretto:17
WORKDIR /app
COPY ./static-resources ${SPRING_DATASOURCE_STATIC}
#RUN mv /app/static-resources /opt/src/static-resources
COPY --from=builder /app/target/vinogradiya-0.0.1-SNAPSHOT.jar /app/vinogradiya.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/vinogradiya.jar"]