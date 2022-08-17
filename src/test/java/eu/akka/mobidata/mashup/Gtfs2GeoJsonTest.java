package eu.akka.mobidata.mashup;

import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.controllers.GtfsController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test Gtfs to GeoJson conversion.
 *
 * @author Mohamed.KARAMI
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Gtfs2GeoJsonTest {
    @Autowired
    private GtfsController gtfsController;

    @DisplayName("Test GTFS format conversion")
    @Test
    public void convertGTFSApiToGeoJson() {
        String gtfsZipUrl = "https://transitfeeds.com/p/tisseo/595/latest/download";
        String geoJsonResponse = gtfsController.convertGtfsApiToGeoJson(gtfsZipUrl, null);
        String type = JsonPath.read(geoJsonResponse, "$.type");

        // assert the response is a feature collection
        Assertions.assertEquals("FeatureCollection", type);
    }
}
