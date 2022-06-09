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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Test Navitia api enrichment.
 *
 * @author Mohamed.KARAMI
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("integration-test")
public class NavitiaEnrichmentTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    private final String HOST = "http://localhost/";
    private final String NAVITIA_REQ = "api/v1/navitia/getJourneys?apiFormat=GeoJson&apiUrl=https://www.overpass-api.de/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;&enrichAttributes=wheelchair, shelter, tactile_paving, bench, bin, lit&fromCoordinates=48.8345631,2.2433581&toCoordinates=48.8775033,2.4400646&targetToken=55af740c-e0e9-4f2b-9387-3bb81a8c7bd4";

    @DisplayName("Test Navitia api returns status OK")
    @Test
    public void checkNavitiaApi() throws MalformedURLException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(new URL(HOST + NAVITIA_REQ).toString(), String.class);

        // assert navitia api is responding
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Test Navitia api enrichment with shelter")
    @Test
    public void enrichNavitiaJourneyApiWithOsm() throws MalformedURLException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                new URL(HOST + NAVITIA_REQ).toString(), String.class);

        JSONArray enriched_properties = JsonPath.read(response.getBody(), "$..stop_point.enriched_properties");

        // assert there is at least one point was enriched with shelter attribute
        Assertions.assertTrue(enriched_properties.stream().anyMatch(eqs -> ((LinkedHashMap) eqs).get("shelter") != null));
    }
}
