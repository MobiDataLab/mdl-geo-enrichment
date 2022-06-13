package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.services.interfaces.IOsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Handles the OpenStreetMap to GeoJson format conversion.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/osm2geojson", produces = MediaType.APPLICATION_JSON_VALUE)
public class Osm2GeoJsonController {

    @Autowired
    IOsmService osmService;

    /**
     * Convert data to geoJson
     *
     * @param osmData osm input Data
     * @return geoJson output
     */
    @RequestMapping(value = "convertOsmDataToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertDataToGeoJson(@ApiParam(value = "Base 64 encoded OSM data content", required = true) String osmData) {
        return GeoJsonManager.convertOsmToGeoJson(new String(Base64.getDecoder().decode(osmData)));
    }

    /**
     * Convert osm api to geoJson
     *
     * @param osmApiUrl osm api url
     * @return geoJson output
     */
    @RequestMapping(value = "convertOsmApiToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertOsmApiToGeoJson(@ApiParam(value = "Target api url (Only OSM api format is supported!)", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String osmApiUrl,
                                  @ApiParam(value = "Source API authorization token") String sourceToken) {
        osmApiUrl = URLDecoder.decode(osmApiUrl, StandardCharsets.UTF_8);
        return osmService.getGeoJsonFromOsmBusStops(osmApiUrl, sourceToken);
    }
}
