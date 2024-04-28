FROM gradle:7.6.4-jdk11 as builder

WORKDIR /app
COPY . .
RUN gradle --no-daemon auth-service:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /app/auth-service/build/libs/auth-service.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar auth-service.jar
