
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
    "stop_point",
    "links",
    "arrival_date_time",
    "additional_informations",
    "departure_date_time",
    "base_arrival_date_time",
    "base_departure_date_time"
})
@Generated("jsonschema2pojo")
public class StopDateTime {

    @JsonProperty("stop_point")
    private StopPoint stopPoint;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("arrival_date_time")
    private String arrivalDateTime;
    @JsonProperty("additional_informations")
    private List<Object> additionalInformations = null;
    @JsonProperty("departure_date_time")
    private String departureDateTime;
    @JsonProperty("base_arrival_date_time")
    private String baseArrivalDateTime;
    @JsonProperty("base_departure_date_time")
    private String baseDepartureDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("stop_point")
    public StopPoint getStopPoint() {
        return stopPoint;
    }

    @JsonProperty("stop_point")
    public void setStopPoint(StopPoint stopPoint) {
        this.stopPoint = stopPoint;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("arrival_date_time")
    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    @JsonProperty("arrival_date_time")
    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @JsonProperty("additional_informations")
    public List<Object> getAdditionalInformations() {
        return additionalInformations;
    }

    @JsonProperty("additional_informations")
    public void setAdditionalInformations(List<Object> additionalInformations) {
        this.additionalInformations = additionalInformations;
    }

    @JsonProperty("departure_date_time")
    public String getDepartureDateTime() {
        return departureDateTime;
    }

    @JsonProperty("departure_date_time")
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    @JsonProperty("base_arrival_date_time")
    public String getBaseArrivalDateTime() {
        return baseArrivalDateTime;
    }

    @JsonProperty("base_arrival_date_time")
    public void setBaseArrivalDateTime(String baseArrivalDateTime) {
        this.baseArrivalDateTime = baseArrivalDateTime;
    }

    @JsonProperty("base_departure_date_time")
    public String getBaseDepartureDateTime() {
        return baseDepartureDateTime;
    }

    @JsonProperty("base_departure_date_time")
    public void setBaseDepartureDateTime(String baseDepartureDateTime) {
        this.baseDepartureDateTime = baseDepartureDateTime;
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
