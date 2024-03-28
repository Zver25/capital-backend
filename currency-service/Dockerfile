FROM gradle:7.6.4-jdk11 as builder

WORKDIR /app
COPY . .
RUN gradle currency-service:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /app/currency-service/build/libs/currency-service.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar currency-service.jar
