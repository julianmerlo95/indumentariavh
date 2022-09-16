FROM openjdk:12-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /backend

COPY . .

EXPOSE 8080

CMD ./gradlew run
