
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
    "from",
    "links",
    "arrival_date_time",
    "co2_emission",
    "to",
    "departure_date_time",
    "geojson",
    "duration",
    "path",
    "type",
    "id",
    "mode",
    "additional_informations",
    "display_informations",
    "base_arrival_date_time",
    "base_departure_date_time",
    "data_freshness",
    "stop_date_times",
    "transfer_type"
})
@Generated("jsonschema2pojo")
public class Section {

    @JsonProperty("from")
    private From from;
    @JsonProperty("links")
    private List<Link> links = null;
    @JsonProperty("arrival_date_time")
    private String arrivalDateTime;
    @JsonProperty("co2_emission")
    private Co2Emission co2Emission;
    @JsonProperty("to")
    private To to;
    @JsonProperty("departure_date_time")
    private String departureDateTime;
    @JsonProperty("geojson")
    private Geojson geojson;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("path")
    private List<Path> path = null;
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("additional_informations")
    private List<String> additionalInformations = null;
    @JsonProperty("display_informations")
    private DisplayInformations displayInformations;
    @JsonProperty("base_arrival_date_time")
    private String baseArrivalDateTime;
    @JsonProperty("base_departure_date_time")
    private String baseDepartureDateTime;
    @JsonProperty("data_freshness")
    private String dataFreshness;
    @JsonProperty("stop_date_times")
    private List<StopDateTime> stopDateTimes = null;
    @JsonProperty("transfer_type")
    private String transferType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("from")
    public From getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(From from) {
        this.from = from;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
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

    @JsonProperty("co2_emission")
    public Co2Emission getCo2Emission() {
        return co2Emission;
    }

    @JsonProperty("co2_emission")
    public void setCo2Emission(Co2Emission co2Emission) {
        this.co2Emission = co2Emission;
    }

    @JsonProperty("to")
    public To getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(To to) {
        this.to = to;
    }

    @JsonProperty("departure_date_time")
    public String getDepartureDateTime() {
        return departureDateTime;
    }

    @JsonProperty("departure_date_time")
    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    @JsonProperty("geojson")
    public Geojson getGeojson() {
        return geojson;
    }

    @JsonProperty("geojson")
    public void setGeojson(Geojson geojson) {
        this.geojson = geojson;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("path")
    public List<Path> getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(List<Path> path) {
        this.path = path;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("additional_informations")
    public List<String> getAdditionalInformations() {
        return additionalInformations;
    }

    @JsonProperty("additional_informations")
    public void setAdditionalInformations(List<String> additionalInformations) {
        this.additionalInformations = additionalInformations;
    }

    @JsonProperty("display_informations")
    public DisplayInformations getDisplayInformations() {
        return displayInformations;
    }

    @JsonProperty("display_informations")
    public void setDisplayInformations(DisplayInformations displayInformations) {
        this.displayInformations = displayInformations;
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

    @JsonProperty("data_freshness")
    public String getDataFreshness() {
        return dataFreshness;
    }

    @JsonProperty("data_freshness")
    public void setDataFreshness(String dataFreshness) {
        this.dataFreshness = dataFreshness;
    }

    @JsonProperty("stop_date_times")
    public List<StopDateTime> getStopDateTimes() {
        return stopDateTimes;
    }

    @JsonProperty("stop_date_times")
    public void setStopDateTimes(List<StopDateTime> stopDateTimes) {
        this.stopDateTimes = stopDateTimes;
    }

    @JsonProperty("transfer_type")
    public String getTransferType() {
        return transferType;
    }

    @JsonProperty("transfer_type")
    public void setTransferType(String transferType) {
        this.transferType = transferType;
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
