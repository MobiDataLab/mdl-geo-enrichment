# docker image to be used for build & test MobiDataMashup
FROM adoptopenjdk/openjdk11:alpine-jre

RUN apk add nodejs npm \
    && npm install -g osmtogeojson

RUN mkdir /app
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8100
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
