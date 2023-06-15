package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.interfaces.INavitiaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the mobility data / journeys in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/navitia", produces = MediaType.APPLICATION_JSON_VALUE)
public class NavitiaController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavitiaController.class);

    @Autowired
    private INavitiaService navitiaService;

    @RequestMapping(value = "getJourneys", method = RequestMethod.GET)
    public @ResponseBody
    String getJourneys(@Nullable @Parameter(description = "Navitia API authorization token", example = "55af740c-e0e9-4f2b-9387-3bb81a8c7bd4") String targetToken,
                       @Parameter(description = "Coordinates of starting point: latitude, longitude", example = "41.822902,12.4057903") String fromCoordinates,
                       @Parameter(description = "Coordinates of the arrival point: latitude, longitude", example = "41.939445,12.5472503") String toCoordinates,
                       @Parameter(description = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                       @Parameter(description = "API format", schema = @Schema(description = "var 1", type = "string", allowableValues = {"GeoJson", "OSM", "GTFS"}, defaultValue = "OSM")) APIFormatEnum apiFormat,
                       @Parameter(description = "API full url", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](41.822902,12.4057903,41.939445,12.5472503);out%20meta;") String apiUrl,
                       @Nullable @Parameter(description = "Source API authorization token") String sourceToken) {

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
    String getLines(@Nullable @Parameter(description = "Navitia API authorization token", example = "55af740c-e0e9-4f2b-9387-3bb81a8c7bd4") String targetToken,
                    @Parameter(description = "Coordinates of starting point: latitude, longitude", example = "41.822902,12.4057903") String fromCoordinates,
                    @Parameter(description = "Coordinates of the arrival point: latitude, longitude", example = "41.939445,12.5472503") String toCoordinates,
                    @Parameter(description = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                    @Parameter(description = "API format", schema = @Schema(description = "var 1", type = "string", allowableValues = {"GeoJson", "OSM", "GTFS"}, defaultValue = "OSM")) APIFormatEnum apiFormat,
                    @Parameter(description = "API full url", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](41.822902,12.4057903,41.939445,12.5472503);out%20meta;") String apiUrl,
                    @Nullable @Parameter(description = "Source API authorization token") String sourceToken) {
        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get lines from Navitia
        String lines = navitiaService.findLines(targetToken, fromCoordinates, toCoordinates);
        if (lines == null) {
            throw new MobilityDataNotFoundException("No Navitia lines found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.Navitia, apiFormat, apiUrl, sourceToken, lines, enrichAttributes);
    }

}
