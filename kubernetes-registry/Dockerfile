FROM gradle:7.6.4-jdk11 as builder

WORKDIR /app
COPY . .
RUN gradle --no-daemon kubernetes-registry:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /app/kubernetes-registry/build/libs/kubernetes-registry.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar kubernetes-registry.jar
