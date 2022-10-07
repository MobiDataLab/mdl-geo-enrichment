package eu.akka.mobidata.mashup;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Test GeoJson api enrichment.
 *
 * @author Mohamed.KARAMI
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GeoJsonEnrichmentTest {

    @Value("${server.http.port}")
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String HOST = "http://localhost:";
    private final String GEOJSON_REQ = "/api/v1/geojson/enrichGeoJsonApi?apiFormat=OSM&enrichAttributes=wheelchair, shelter, tactile_paving, bench, bin, lit&sourceApiUrl=https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;&targetApiUrl=https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;";

    @DisplayName("Test here routes enrichment")
    @Test
    public void testGeoJsonEnrichment() throws MalformedURLException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                new URL(HOST + port + GEOJSON_REQ).toString(), String.class);

        JSONArray enriched_properties = JsonPath.read(response.getBody(), "$..enriched_properties");

        // assert there is at least one point was enriched with shelter attribute
        Assertions.assertTrue(enriched_properties.stream().anyMatch(eqs -> ((LinkedHashMap) eqs).get("wheelchair") != null));
    }
}
