
package eu.akka.mobidata.mashup.domain.navitia;

import java.util.HashMap;
import java.util.List;
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
    "active_periods",
    "week_pattern"
})
@Generated("jsonschema2pojo")
public class Calendar {

    @JsonProperty("active_periods")
    private List<ActivePeriod> activePeriods = null;
    @JsonProperty("week_pattern")
    private WeekPattern weekPattern;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("active_periods")
    public List<ActivePeriod> getActivePeriods() {
        return activePeriods;
    }

    @JsonProperty("active_periods")
    public void setActivePeriods(List<ActivePeriod> activePeriods) {
        this.activePeriods = activePeriods;
    }

    @JsonProperty("week_pattern")
    public WeekPattern getWeekPattern() {
        return weekPattern;
    }

    @JsonProperty("week_pattern")
    public void setWeekPattern(WeekPattern weekPattern) {
        this.weekPattern = weekPattern;
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
