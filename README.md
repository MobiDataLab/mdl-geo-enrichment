# mdl-geo-enrichment [![Build Status](https://github.com/wanam/mdl-geo-enrichment/actions/workflows/github-actions.yml/badge.svg)](https://github.com/wanam/mdl-geo-enrichment/actions/workflows/github-actions.yml)

## Description

Mashup REST API for mobility data.

This is a geographical enrichment demonstrator that Aggregates and enrich information from the following 3 sources:

* Navitia (https://api.navitia.io)
* OpenStreetMap (https://api.openstreetmap.org and https://overpass-api.de)
* Here (https://here.com)

Geographical demonstrator supports 3 standard data format for enrichment:
* OSM: Open Street Map
* GTFS: General Transit Feed Specification
* GeoJson: Representation of geographical features based on Json

An example of API enrichment is illustrated in the bellow sequence diagram:

![Sequence Diagram](https://www.plantuml.com/plantuml/png/TP51ImD138Nl-oj2B-f1l7kGYXPH44fRVq1s9zQHRbAJBDX_RuPTKTlnbc5UtlTucPkZ-j3lcXSZXSiRc3rBc-_H4MwNZpEuXWLhxjuWT5joU991cGvdLhwR8t5PtuowrMAz-NPuYBVAu6Hm1U-ZWJKdoYfz5NkPjw2q5pQg4TVXsD5lOW7k9RCRdBCaiWl8_2hQesTXQ0_Goi0fV6FuR4uTQacM_nhMGXrltuagxT1f2XO5TY8Q6R3qWfo0iPyc6YjN-XZ8d1AWk-QsD5XZbTFzN8hIXFWJSKwSuinV)

## Prerequisites
* Install OpenJDK 17
* Install Node.js
* Install OsmToGeoJson module
* Install GtfsToGeoJson module
```
$ npm install -g osmtogeojson
$ npm install -g gtfs-to-geojson
```

## Build and Run
```
$ mvn install -DskipTests
$ mvn spring-boot:run
```

The application is also packaged as a standalone jar which can be run as follows:
```
$ java -jar target/mdl-geo-enrichment-0.0.1-SNAPSHOT.jar
```

## Run as docker image
A docker image is built automatically upon main branch and pushed to docker hub with the deployed application and all prerequisites installed: openjdk11-jre & nodejs & osmtogeojson module
```
docker run -d -p 80:80 -p 443:443 wanam/mdl-geo-enrichment:latest --pull=always
```

## Browse Swagger UI
Once the application is up, you can browse the apis on your local server through [HTTP](http://localhost/swagger-ui/) and [HTTPS](https://localhost/swagger-ui/)

Enriched data will be stored on a separate attribute 'enriched_properties' for each stop point on the api response.