# docker image to be used for MobiDataMashup's deployment
FROM alpine:latest

WORKDIR /usr/app
COPY target/*.jar /usr/app/app.jar

# install openjdk11-jre & nodejs & osmtogeojson module
RUN apk --no-cache add openjdk11-jre --repository=http://dl-cdn.alpinelinux.org/alpine/latest-stable/community \
    && apk --no-cache add nodejs npm \
    && npm install -g osmtogeojson

# expose required ports
EXPOSE 80 443
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/app/app.jar"]
