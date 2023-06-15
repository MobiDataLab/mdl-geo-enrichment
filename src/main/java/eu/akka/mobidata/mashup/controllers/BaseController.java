package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.model.GenericJsonCriteria;
import eu.akka.mobidata.mashup.services.interfaces.IGenericJsonService;
import eu.akka.mobidata.mashup.services.interfaces.IGeoJsonService;
import eu.akka.mobidata.mashup.services.interfaces.IGtfsService;
import eu.akka.mobidata.mashup.services.interfaces.IOsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import eu.akka.mobidata.mashup.util.JsonParser;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the base controller of mobility data REST API.
 *
 * @author Mohamed.KARAMI
 */
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(responseCode = "429", description = "Too Many Requests")})
public class BaseController {

    @Autowired
    protected IOsmService osmService;

    @Autowired
    protected IGeoJsonService geoJsonService;

    @Autowired
    protected IGtfsService gtfsService;

    @Autowired
    protected IGenericJsonService genericJsonService;

    protected String getEnrichedData(TargetAPIFormatEnum targetApiFormat, APIFormatEnum apiFormat, String apiUrl, String sourceToken, String data, String enrichAttributes) {
        String busStops;
        if (APIFormatEnum.OSM.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            busStops = osmService.getGeoJsonFromOsmBusStops(apiUrl, sourceToken);
        } else if (APIFormatEnum.GeoJson.equals(apiFormat)) {
            // get bus stops for the same coordinates
            busStops = geoJsonService.getGeoJsonBusStops(apiUrl, sourceToken);
        } else if (APIFormatEnum.GTFS.equals(apiFormat)) {
            busStops = gtfsService.getGeoJsonFromGtfsBusStops(apiUrl, sourceToken);
        } else {
            throw new RuntimeException("Unsupported Data Format!");
        }

        // load features from geo json response
        GeoJsonManager geoJsonManager = new GeoJsonManager(data, busStops);

        switch (targetApiFormat) {
            case Navitia -> {
                return geoJsonManager.aggregateNavitiaBusStops(enrichAttributes);
            }
            case Here -> {
                return geoJsonManager.aggregateHereBusStops(enrichAttributes);
            }
            case OSM -> {
                return geoJsonManager.aggregateOsmBusStops(enrichAttributes);
            }
            case GeoJson -> {
                return geoJsonManager.aggregateGeoJsonBusStops(enrichAttributes);
            }
            default -> throw new RuntimeException("Unsupported Data Format!");
        }

    }

    protected String getEnrichedData(String apiUrl, String sourceToken, String data, String attributes, GenericJsonCriteria targetCriteria, GenericJsonCriteria sourceCriteria) {
        String busStops;
        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);
        busStops = genericJsonService.getJsonStops(apiUrl, sourceToken);

        // load json response to the parser
        JsonParser jsonParser = new JsonParser(data, busStops);
        return jsonParser.aggregateJsonStops(attributes, targetCriteria, sourceCriteria);
    }
}
