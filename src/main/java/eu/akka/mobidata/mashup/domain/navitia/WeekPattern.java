
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
    "monday",
    "tuesday",
    "friday",
    "wednesday",
    "thursday",
    "sunday",
    "saturday"
})
@Generated("jsonschema2pojo")
public class WeekPattern {

    @JsonProperty("monday")
    private Boolean monday;
    @JsonProperty("tuesday")
    private Boolean tuesday;
    @JsonProperty("friday")
    private Boolean friday;
    @JsonProperty("wednesday")
    private Boolean wednesday;
    @JsonProperty("thursday")
    private Boolean thursday;
    @JsonProperty("sunday")
    private Boolean sunday;
    @JsonProperty("saturday")
    private Boolean saturday;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("monday")
    public Boolean getMonday() {
        return monday;
    }

    @JsonProperty("monday")
    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    @JsonProperty("tuesday")
    public Boolean getTuesday() {
        return tuesday;
    }

    @JsonProperty("tuesday")
    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    @JsonProperty("friday")
    public Boolean getFriday() {
        return friday;
    }

    @JsonProperty("friday")
    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    @JsonProperty("wednesday")
    public Boolean getWednesday() {
        return wednesday;
    }

    @JsonProperty("wednesday")
    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    @JsonProperty("thursday")
    public Boolean getThursday() {
        return thursday;
    }

    @JsonProperty("thursday")
    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    @JsonProperty("sunday")
    public Boolean getSunday() {
        return sunday;
    }

    @JsonProperty("sunday")
    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }

    @JsonProperty("saturday")
    public Boolean getSaturday() {
        return saturday;
    }

    @JsonProperty("saturday")
    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
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
