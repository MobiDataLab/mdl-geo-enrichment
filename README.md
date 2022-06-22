# mobidata-mashup [![Build Status](https://app.travis-ci.com/wanam/mobidata-mashup.svg?token=c9NQasTAc5rfBPApkkwn&branch=main)](https://app.travis-ci.com/wanam/mobidata-mashup)

## Description

Mashup REST API for mobility data.

Aggregates information from the following 3 sources into a new REST API:

* Navitia (https://api.navitia.io)
* OpenStreetMap (https://api.openstreetmap.org and https://overpass-api.de)
* Here (https://here.com)

## Prerequisites
* Install OpenJDK 11
* Install Node.js
* Install OsmToGeoJson module
```
$ npm install -g osmtogeojson
```

## Build and Run
```
$ mvn install -DskipTests
$ mvn spring-boot:run
```

The application is also packaged as a standalone jar which can be run as follows:
```
$ java -jar target/mobidata-mashup-0.0.1-SNAPSHOT.jar
```

## Run as docker image
A docker image is built automatically upon main branch and pushed to docker hub with the deployed application and all prerequisites installed: openjdk11-jre & nodejs & osmtogeojson module
```
docker run -d -p 80:80 -p 443:443 wanam/mobidatalab:latest --pull=always
```

## Browse Swagger UI
Once the application is up, you can browse the apis on your local server through [HTTP](http://localhost/swagger-ui/) and [HTTPS](https://localhost/swagger-ui/)

Enriched data will be stored on a separate attribute 'enriched_properties' for each stop point on the api response.