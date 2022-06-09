package eu.akka.mobidata.mashup;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Test here api enrichment.
 *
 * @author Mohamed.KARAMI
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("integration-test")
public class HereEnrichmentTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    private final String HOST = "http://localhost:8100/";

    private final String HERE_ROUTES_REQ = "api/v1/here/getRoutes?apiKey=0PMpb1W_5iihYGu7UrBWsR8fI6Utopf52hFBKOwl7Xc&apiFormat=GeoJson&apiUrl=https://www.overpass-api.de/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;&enrichAttributes=wheelchair, shelter, tactile_paving, bench, bin, lit&fromCoordinates=48.8345631,2.2433581&toCoordinates=48.8775033,2.4400646";

    private final String HERE_STATIONS_REQ = "api/v1/here/getNearStations?apiFormat=GeoJson&apiKey=0PMpb1W_5iihYGu7UrBWsR8fI6Utopf52hFBKOwl7Xc&apiUrl=https://www.overpass-api.de/api/interpreter?data=[out:json];node[highway](48.856892, 2.332623,48.896892, 2.372623);node[railway](48.856892, 2.332623,48.896892, 2.372623);out%20meta;&enrichAttributes=wheelchair, shelter, tactile_paving, bench, bin, lit&coordinates=48.8545631,2.3433581";

    @DisplayName("Test here station enrichment")
    @Test
    public void testHereStationsEnrichment() throws MalformedURLException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                new URL(HOST + HERE_STATIONS_REQ).toString(), String.class);

        JSONArray enriched_properties = JsonPath.read(response.getBody(), "$..enriched_properties");

        // assert there is at least one point was enriched with shelter attribute
        Assertions.assertTrue(enriched_properties.stream().anyMatch(eqs -> ((LinkedHashMap) eqs).get("wheelchair") != null));
    }

    @DisplayName("Test here routes enrichment")
    @Test
    public void testHereRoutesEnrichment() throws MalformedURLException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                new URL(HOST + HERE_ROUTES_REQ).toString(), String.class);

        JSONArray enriched_properties = JsonPath.read(response.getBody(), "$..enriched_properties");

        // assert there is at least one point was enriched with shelter attribute
        Assertions.assertTrue(enriched_properties.stream().anyMatch(eqs -> ((LinkedHashMap) eqs).get("wheelchair") != null));
    }
}
