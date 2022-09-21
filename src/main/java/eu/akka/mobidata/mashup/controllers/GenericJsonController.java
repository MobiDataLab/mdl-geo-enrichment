package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.model.GenericJsonCriteria;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    String enrichJsonApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichmentAttributes,
                         @ApiParam(value = "Target attributes root path of the point using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..stop_point") String targetAttributesRootPath,
                         @ApiParam(value = "Source attributes root path of the point using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..elements") String sourceAttributesRootPath,
                         @ApiParam(value = "The parent path of the enrichment attributes on the source api, using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..elements.tags") String enrichmentAttributesPath,
                         @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://api.navitia.io/v1/journeys?from=2.2433581;48.8345631&to=2.4400646;48.8775033&allowed_id[]=physical_mode:Bus") String targetApiUrl,
                         @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                         @ApiParam(value = "Target API authorization token", example = "55af740c-e0e9-4f2b-9387-3bb81a8c7bd4") String targetToken,
                         @ApiParam(value = "Source API authorization token") String sourceToken,
                         @ApiParam(value = "The path of the coordinates node on the target api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..stop_point.coord") String targetCoordsPath,
                         @ApiParam(value = "The path of the coordinates node on the source api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..elements") String sourceCoordsPath,
                         @ApiParam(value = "The path of the point name on the target api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..stop_point.name") String targetNamePath,
                         @ApiParam(value = "The path of the point name on the source api using [Jsonpath expressions](https://goessner.net/articles/JsonPath/index.html#e2)", required = true, example = "$..elements.tags.name") String sourceNamePath) {

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
