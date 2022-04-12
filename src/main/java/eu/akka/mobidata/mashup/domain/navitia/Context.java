
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
    "timezone",
    "current_datetime",
    "car_direct_path"
})
@Generated("jsonschema2pojo")
public class Context {

    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("current_datetime")
    private String currentDatetime;
    @JsonProperty("car_direct_path")
    private CarDirectPath carDirectPath;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("current_datetime")
    public String getCurrentDatetime() {
        return currentDatetime;
    }

    @JsonProperty("current_datetime")
    public void setCurrentDatetime(String currentDatetime) {
        this.currentDatetime = currentDatetime;
    }

    @JsonProperty("car_direct_path")
    public CarDirectPath getCarDirectPath() {
        return carDirectPath;
    }

    @JsonProperty("car_direct_path")
    public void setCarDirectPath(CarDirectPath carDirectPath) {
        this.carDirectPath = carDirectPath;
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
