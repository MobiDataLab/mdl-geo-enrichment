package eu.akka.mobidata.mashup;

import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.controllers.Osm2GeoJsonController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test Osm to GeoJson conversion.
 *
 * @author Mohamed.KARAMI
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("integration-test")
public class Osm2GeoJsonTest {

    @Autowired
    private Osm2GeoJsonController osm2GeoJsonController;

    @DisplayName("Test OSM format conversion")
    @Test
    public void convertOSMApiToGeoJson() {
        String OsmApiUrl = "https://www.overpass-api.de/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;";
        String geoJsonResponse = osm2GeoJsonController.convertOsmApiToGeoJson(OsmApiUrl, null);
        String type = JsonPath.read(geoJsonResponse, "$.type");

        // assert the response is a feature collection
        Assertions.assertEquals("FeatureCollection", type);
    }
}
