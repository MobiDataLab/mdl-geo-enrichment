# mobidata-mashup

## Description

Mashup REST API for mobility data.

Aggregates information from the following two sources into a new REST API:

* Navitia (https://api.navitia.io)
* OpenStreetMap (https://api.openstreetmap.org and https://overpass-api.de)


## Prerequisites
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
