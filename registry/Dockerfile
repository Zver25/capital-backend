FROM gradle:7-jdk11 as builder

COPY . /home/gradle
RUN gradle registry:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /home/gradle/registry/build/libs/registry.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar registry.jar
