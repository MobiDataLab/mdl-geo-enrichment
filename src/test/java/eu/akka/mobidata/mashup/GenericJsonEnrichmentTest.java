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
 * Test here api enrichment.
 *
 * @author Mohamed.KARAMI
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GenericJsonEnrichmentTest {

    @Value("${server.http.port}")
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String HOST = "http://localhost:";
    private final String HERE_ROUTES_REQ = "/api/v1/json/enrichJsonApi?enrichmentAttributes=wheelchair, shelter, tactile_paving, bench, bin, lit&enrichmentAttributesPath=$..elements.tags&sourceApiUrl=https%3A%2F%2Foverpass.kumi.systems%2Fapi%2Finterpreter%3Fdata%3D%5Bout%3Ajson%5D%3Bnode%5Bhighway%3Dbus_stop%5D(48.8345631%2C2.2433581%2C48.8775033%2C2.4400646)%3Bout%2520meta%3B&sourceAttributesRootPath=$..elements&sourceCoordsPath=$..elements&sourceNamePath=$..elements.tags.name&targetApiUrl=https%3A%2F%2Fapi.navitia.io%2Fv1%2Fjourneys%3Ffrom%3D2.2433581%3B48.8345631%26to%3D2.4400646%3B48.8775033%26allowed_id%5B%5D%3Dphysical_mode%3ABus&targetAttributesRootPath=$..stop_point&targetCoordsPath=$..stop_point.coord&targetNamePath=$..stop_point.name&targetToken=55af740c-e0e9-4f2b-9387-3bb81a8c7bd4";


    @DisplayName("Generic json enrichment Navitia/OSM")
    @Test
    public void testGenericJsonEnrichment() throws MalformedURLException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                new URL(HOST + port + HERE_ROUTES_REQ).toString(), String.class);

        JSONArray enriched_properties = JsonPath.read(response.getBody(), "$..enriched_properties");

        // assert there is at least one point was enriched with shelter attribute
        Assertions.assertTrue(enriched_properties.stream().anyMatch(eqs -> ((LinkedHashMap) eqs).get("wheelchair") != null));
    }
}
