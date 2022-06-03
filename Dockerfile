# docker image to be used for MobiDataMashup's deployment
FROM alpine:latest

WORKDIR /usr/app
COPY target/*.jar /usr/app/app.jar

# install openjdk11-jre & npm & osmtogeojson module
RUN apk add --no-cache openjdk11-jre nodejs npm \
    && npm install -g osmtogeojson

# expose required ports
EXPOSE 80 443
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/app/app.jar"]
