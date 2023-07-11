FROM gradle:7-jdk11 as builder

COPY . /home/gradle
RUN gradle report-service:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /home/gradle/report-service/build/libs/report-service.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar report-service.jar
