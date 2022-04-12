
package eu.akka.mobidata.mashup.domain.navitia;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "co2_emission"
})
@Generated("jsonschema2pojo")
public class CarDirectPath {

    @JsonProperty("co2_emission")
    private Co2Emission co2Emission;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("co2_emission")
    public Co2Emission getCo2Emission() {
        return co2Emission;
    }

    @JsonProperty("co2_emission")
    public void setCo2Emission(Co2Emission co2Emission) {
        this.co2Emission = co2Emission;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
