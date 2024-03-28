FROM gradle:7.6.4-jdk11 as builder

WORKDIR /app
COPY . .
RUN gradle registry:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /app/registry/build/libs/registry.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar registry.jar
