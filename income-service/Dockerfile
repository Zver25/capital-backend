FROM gradle:7-jdk11 as builder

COPY . /home/gradle
RUN gradle income-service:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /home/gradle/income-service/build/libs/income-service.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar income-service.jar
