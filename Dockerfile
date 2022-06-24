# docker image to be used for MobiDataMashup's deployment
FROM alpine:3.16.0

WORKDIR /usr/app
COPY target/*.jar /usr/app/app.jar

# install openjdk11-jre & npm & osmtogeojson module
RUN apk --update add --no-cache openjdk11-jre nodejs npm \
    && npm install -g osmtogeojson \
    && npm install -g gtfs-to-geojson

# expose required ports
EXPOSE 80 443
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/usr/app/app.jar"]
