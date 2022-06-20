package eu.akka.mobidata.mashup.controllers;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles the enrichment of a generic mobility data REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/json", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 429, message = "Too Many Requests")})
public class JsonEnrichmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonEnrichmentController.class);

    @RequestMapping(value = "enrichJsonApi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String enrichJsonApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                         @ApiParam(value = "Target attributes parent's path (using Jsonpath expressions: https://goessner.net/articles/JsonPath/)", required = true, example = "$..stop_point.equipments") String targetAttributesParentPath,
                         @ApiParam(value = "Source attributes parent's path (using Jsonpath expressions: https://goessner.net/articles/JsonPath/)", required = true, example = "$..elements.tags") String sourceAttributesParentPath,
                         @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                         @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                         @ApiParam(value = "Target API authorization token") String targetToken,
                         @ApiParam(value = "Source API authorization token") String sourceToken,
                         @ApiParam(value = "The path of the target point coordinate's path (using Jsonpath expressions: https://goessner.net/articles/JsonPath/)", required = true, example = "$..stop_point.coordinates") String targetNamePath,
                         @ApiParam(value = "The path of the source point coordinate's path (using Jsonpath expressions: https://goessner.net/articles/JsonPath/)", required = true, example = "$..elements.coords") String sourceNamePath) {

        throw new RuntimeException("Not yet implemented!");
    }

}
