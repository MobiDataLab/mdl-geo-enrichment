package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.interfaces.INavitiaService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the mobility data / journeys in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/navitia")
public class NavitiaController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavitiaController.class);

    @Autowired
    private INavitiaService navitiaService;

    @RequestMapping(value = "getJourneys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getJourneys(@ApiParam(value = "Navitia API authorization token", example = "55af740c-e0e9-4f2b-9387-3bb81a8c7bd4") String targetToken,
                       @ApiParam(value = "Coordinates of starting point: latitude, longitude", required = true, example = "48.8345631,2.2433581") String fromCoordinates,
                       @ApiParam(value = "Coordinates of the arrival point: latitude, longitude", required = true, example = "48.8775033,2.4400646") String toCoordinates,
                       @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                       @ApiParam(value = "API format", allowableValues = "GeoJson, OSM, GTFS", required = true) APIFormatEnum apiFormat,
                       @ApiParam(value = "API full url", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String apiUrl,
                       @ApiParam(value = "Source API authorization token") String sourceToken) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get journeys from Navitia
        String journeys = navitiaService.findJsonJourneys(targetToken, fromCoordinates, toCoordinates);

        if (journeys == null) {
            throw new MobilityDataNotFoundException("No Navitia journeys found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.Navitia, apiFormat, apiUrl, sourceToken, journeys, enrichAttributes);
    }

    /**
     * Returns the aggregated information for lines.
     *
     * @return the JSON representation of the lines
     */
    @RequestMapping(value = "getLines", method = RequestMethod.GET)
    public @ResponseBody
    String getLines(@ApiParam(value = "Navitia API authorization token", example = "55af740c-e0e9-4f2b-9387-3bb81a8c7bd4") String targetToken,
                    @ApiParam(value = "Coordinates of starting point: latitude, longitude", required = true, example = "48.8345631,2.2433581") String fromCoordinates,
                    @ApiParam(value = "Coordinates of the arrival point: latitude, longitude", required = true, example = "48.8775033,2.4400646") String toCoordinates,
                    @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                    @ApiParam(value = "API format", allowableValues = "GeoJson, OSM, GTFS", required = true) APIFormatEnum apiFormat,
                    @ApiParam(value = "API full url", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String apiUrl,
                    @ApiParam(value = "Source API authorization token") String sourceToken) {
        // Get lines from Navitia
        String lines = navitiaService.findLines(targetToken, fromCoordinates, toCoordinates);
        if (lines == null) {
            throw new MobilityDataNotFoundException("No Navitia lines found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.Navitia, apiFormat, apiUrl, sourceToken, lines, enrichAttributes);
    }

}
