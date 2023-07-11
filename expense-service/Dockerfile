FROM gradle:7-jdk11 as builder

COPY . /home/gradle
RUN gradle expense-service:bootJar

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.19_7
WORKDIR /app
COPY --from=builder /home/gradle/expense-service/build/libs/expense-service.jar .
EXPOSE 8761
ENTRYPOINT exec java -jar expense-service.jar
