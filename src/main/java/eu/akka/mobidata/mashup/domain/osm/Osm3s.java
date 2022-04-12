
package eu.akka.mobidata.mashup.domain.osm;

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
    "timestamp_osm_base",
    "timestamp_areas_base",
    "copyright"
})
@Generated("jsonschema2pojo")
public class Osm3s {

    @JsonProperty("timestamp_osm_base")
    private String timestampOsmBase;
    @JsonProperty("timestamp_areas_base")
    private String timestampAreasBase;
    @JsonProperty("copyright")
    private String copyright;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestamp_osm_base")
    public String getTimestampOsmBase() {
        return timestampOsmBase;
    }

    @JsonProperty("timestamp_osm_base")
    public void setTimestampOsmBase(String timestampOsmBase) {
        this.timestampOsmBase = timestampOsmBase;
    }

    @JsonProperty("timestamp_areas_base")
    public String getTimestampAreasBase() {
        return timestampAreasBase;
    }

    @JsonProperty("timestamp_areas_base")
    public void setTimestampAreasBase(String timestampAreasBase) {
        this.timestampAreasBase = timestampAreasBase;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
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
