package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.impl.GtfsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the enrichment of a GTFS mobility data API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/gtfs", produces = MediaType.APPLICATION_JSON_VALUE)
public class GtfsController extends BaseController {

    @Autowired
    GtfsService gtfsService;

    @RequestMapping(value = "enrichGtfsApi", method = RequestMethod.GET)
    public @ResponseBody
    String enrichGtfsApi(@Parameter(description = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                         @Parameter(description = "Url of the target API to be enriched", example = "https://transitfeeds.com/p/tisseo/595/latest/download") String targetApiUrl,
                         @Parameter(description = "Url of the source API to be used for enrichment", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](43.5740109,1.3833413,43.6073069,1.4512363);out%20meta;") String sourceApiUrl,
                         @Parameter(description = "source API format", schema = @Schema(description = "var 1", type = "string", allowableValues = {"GeoJson", "OSM", "GTFS"}, defaultValue = "OSM")) APIFormatEnum apiFormat,
                         @Nullable @Parameter(description = "Target API authorization token") String targetToken,
                         @Nullable @Parameter(description = "Source API authorization token") String sourceToken) {

        targetApiUrl = URLDecoder.decode(targetApiUrl, StandardCharsets.UTF_8);
        sourceApiUrl = URLDecoder.decode(sourceApiUrl, StandardCharsets.UTF_8);

        // Get bus stops from open street map api
        String geoJsonStops = gtfsService.getGeoJsonFromGtfsBusStops(targetApiUrl, targetToken);

        if (geoJsonStops == null) {
            throw new MobilityDataNotFoundException("No Bus stops found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.GeoJson, apiFormat, sourceApiUrl, sourceToken, geoJsonStops, enrichAttributes);
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
    String convertGtfsApiToGeoJson(@Parameter(description = "URL of GTFS zip file", example = "https://transitfeeds.com/p/tisseo/595/latest/download") String gtfsApiUrl,
                                   @Nullable @Parameter(description = "Authorization token") String targetToken) {

        gtfsApiUrl = URLDecoder.decode(gtfsApiUrl, StandardCharsets.UTF_8);
        return gtfsService.getGeoJsonFromGtfsBusStops(gtfsApiUrl, targetToken);
    }

}
