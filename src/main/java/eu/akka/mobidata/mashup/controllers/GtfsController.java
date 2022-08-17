package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.services.interfaces.IGtfsService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the enrichment of a GTFS mobility data API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/gtfs")
public class GtfsController extends BaseController {

    @RequestMapping(value = "enrichGtfsApi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String enrichGtfsApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                         @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                         @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                         @ApiParam(value = "source API format", allowableValues = "GTFS, GeoJson, OSM", required = true) APIFormatEnum apiFormat,
                         @ApiParam(value = "Target API authorization token") String targetToken,
                         @ApiParam(value = "Source API authorization token") String sourceToken) {

        throw new RuntimeException("Not yet implemented!");
    }

    /**
     * Convert gtfs data to geoJson
     *
     * @param gtfsFile gtfs zip file
     * @return geoJson output
     */
    @RequestMapping(
            value = "convertGtfsFileToGeoJson",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    String convertGtfsFileToGeoJson(@RequestPart(value = "GTFS file") MultipartFile gtfsFile) {
        try {
            return gtfsService.getGeoJsonFromGtfsFile(gtfsFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert gtfs api to geoJson
     *
     * @param gtfsApiUrl gtfs api url
     * @return geoJson output
     */
    @RequestMapping(value = "convertGtfsApiToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertGtfsApiToGeoJson(@ApiParam(value = "URL of GTFS zip file", required = true, example = "https://transitfeeds.com/p/tisseo/595/latest/download") String gtfsApiUrl,
                                   @ApiParam(value = "Authorization token") String targetToken) {

        gtfsApiUrl = URLDecoder.decode(gtfsApiUrl, StandardCharsets.UTF_8);
        return gtfsService.getGeoJsonFromGtfsBusStops(gtfsApiUrl, targetToken);
    }

}
