FROM gradle:7-jdk11 as builder

COPY . /home/gradle
RUN gradle currency-service:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /home/gradle/currency-service/build/libs/currency-service.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar currency-service.jar
