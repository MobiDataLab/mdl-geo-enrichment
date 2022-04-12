
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
    "status",
    "distances",
    "links",
    "tags",
    "nb_transfers",
    "durations",
    "arrival_date_time",
    "calendars",
    "departure_date_time",
    "requested_date_time",
    "fare",
    "co2_emission",
    "type",
    "duration",
    "sections"
})
@Generated("jsonschema2pojo")
public class Journey {

    @JsonProperty("status")
    private String status;
    @JsonProperty("distances")
    private Distances distances;
    @JsonProperty("links")
    private List<Link> links = null;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("nb_transfers")
    private Integer nbTransfers;
    @JsonProperty("durations")
    private Durations durations;
    @JsonProperty("arrival_date_time")
    private String arrivalDateTime;
    @JsonProperty("calendars")
    private List<Calendar> calendars = null;
    @JsonProperty("departure_date_time")
    private String departureDateTime;
    @JsonProperty("requested_date_time")
    private String requestedDateTime;
    @JsonProperty("fare")
    private Fare fare;
    @JsonProperty("co2_emission")
    private Co2Emission co2Emission;
    @JsonProperty("type")
    private String type;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("sections")
    private List<Section> sections = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("distances")
    public Distances getDistances() {
        return distances;
    }

    @JsonProperty("distances")
    public void setDistances(Distances distances) {
        this.distances = distances;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("nb_transfers")
    public Integer getNbTransfers() {
        return nbTransfers;
    }

    @JsonProperty("nb_transfers")
    public void setNbTransfers(Integer nbTransfers) {
        this.nbTransfers = nbTransfers;
    }

    @JsonProperty("durations")
    public Durations getDurations() {
        return durations;
    }

    @JsonProperty("durations")
    public void setDurations(Durations durations) {
        this.durations = durations;
    }

    @JsonProperty("arrival_date_time")
    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    @JsonProperty("arrival_date_time")
    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @JsonProperty("calendars")
    public List<Calendar> getCalendars() {
        return calendars;
    }

    @JsonProperty("calendars")
    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    @JsonProperty("departure_date_time")
    public String getDepartureDateTime() {
        return departureDateTime;
    }

    @JsonProperty("departure_date_time")
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    @JsonProperty("requested_date_time")
    public String getRequestedDateTime() {
        return requestedDateTime;
    }

    @JsonProperty("requested_date_time")
    public void setRequestedDateTime(String requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    @JsonProperty("fare")
    public Fare getFare() {
        return fare;
    }

    @JsonProperty("fare")
    public void setFare(Fare fare) {
        this.fare = fare;
    }

    @JsonProperty("co2_emission")
    public Co2Emission getCo2Emission() {
        return co2Emission;
    }

    @JsonProperty("co2_emission")
    public void setCo2Emission(Co2Emission co2Emission) {
        this.co2Emission = co2Emission;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("sections")
    public List<Section> getSections() {
        return sections;
    }

    @JsonProperty("sections")
    public void setSections(List<Section> sections) {
        this.sections = sections;
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
