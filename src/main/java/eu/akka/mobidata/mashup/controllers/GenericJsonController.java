package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.model.GenericJsonCriteria;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the enrichment of a generic mobility data REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/json", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenericJsonController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericJsonController.class);

    @RequestMapping(value = "enrichJsonApi", method = RequestMethod.GET)
    public @ResponseBody
    String enrichJsonApi(@Parameter(description = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichmentAttributes,
                         @Parameter(description = "Target attributes root path of the point using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..stop_point") String targetAttributesRootPath,
                         @Parameter(description = "Source attributes root path of the point using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..elements") String sourceAttributesRootPath,
                         @Parameter(description = "The parent path of the enrichment attributes on the source api, using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..elements.tags") String enrichmentAttributesPath,
                         @Parameter(description = "Url of the target API to be enriched", example = "https://api.navitia.io/v1/journeys?from=4.3398019;50.8439547&to=4.3056933;50.8396064&allowed_id[]=physical_mode:Bus") String targetApiUrl,
                         @Parameter(description = "Url of the source API to be used for enrichment", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](50.8396064,4.3056933,50.8439547,4.3398019);out%20meta;") String sourceApiUrl,
                         @Parameter(description = "Target API authorization token", example = "55af740c-e0e9-4f2b-9387-3bb81a8c7bd4") String targetToken,
                         @Nullable @Parameter(description = "Source API authorization token") String sourceToken,
                         @Parameter(description = "The path of the coordinates node on the target api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..stop_point.coord") String targetCoordsPath,
                         @Parameter(description = "The path of the coordinates node on the source api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..elements") String sourceCoordsPath,
                         @Parameter(description = "The path of the point name on the target api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..stop_point.name") String targetNamePath,
                         @Parameter(description = "The path of the point name on the source api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", example = "$..elements.tags.name") String sourceNamePath) {

        targetApiUrl = URLDecoder.decode(targetApiUrl, StandardCharsets.UTF_8);
        sourceApiUrl = URLDecoder.decode(sourceApiUrl, StandardCharsets.UTF_8);

        // Get stops from a generic json api
        String stops = genericJsonService.getJsonStops(targetApiUrl, targetToken);

        if (stops == null) {
            throw new MobilityDataNotFoundException("No stops found!");
        }

        // fill attributes paths
        GenericJsonCriteria targetCriteria = new GenericJsonCriteria(targetCoordsPath, targetNamePath, targetAttributesRootPath);
        GenericJsonCriteria sourceCriteria = new GenericJsonCriteria(sourceCoordsPath, sourceNamePath, sourceAttributesRootPath, enrichmentAttributesPath);

        return getEnrichedData(sourceApiUrl, sourceToken, stops, enrichmentAttributes, targetCriteria, sourceCriteria);
    }
}
