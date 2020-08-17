FROM openjdk:8-jdk-alpine as builder

ENV WORKDIR=/srv/app

RUN mkdir -p $WORKDIR

ENV PORT=8080

WORKDIR $WORKDIR

COPY build.gradle settings.gradle gradlew gradle.properties ./
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew build

FROM openjdk:8-jdk-alpine
ENV WORKDIR=/srv/app

RUN mkdir -p $WORKDIR
WORKDIR $WORKDIR
copy --from=builder /srv/app/build/libs/*-all.jar ./app.jar

EXPOSE 8080