
package eu.akka.mobidata.mashup.domain.navitia;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "network",
        "links",
        "color",
        "routes",
        "geojson",
        "text_color",
        "physical_modes",
        "codes",
        "closing_time",
        "opening_time",
        "commercial_mode",
        "id",
        "name"
})
@Generated("jsonschema2pojo")
public class Line {

    @JsonProperty("code")
    private String code;
    @JsonProperty("network")
    private Network network;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("color")
    private String color;
    @JsonProperty("routes")
    private List<Route> routes = null;
    @JsonProperty("geojson")
    private Geojson geojson;
    @JsonProperty("text_color")
    private String textColor;
    @JsonProperty("physical_modes")
    private List<PhysicalMode> physicalModes = null;
    @JsonProperty("codes")
    private List<Object> codes = null;
    @JsonProperty("closing_time")
    private String closingTime;
    @JsonProperty("opening_time")
    private String openingTime;
    @JsonProperty("commercial_mode")
    private CommercialMode commercialMode;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("network")
    public Network getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(Network network) {
        this.network = network;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("routes")
    public List<Route> getRoutes() {
        return routes;
    }

    @JsonProperty("routes")
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @JsonProperty("geojson")
    public Geojson getGeojson() {
        return geojson;
    }

    @JsonProperty("geojson")
    public void setGeojson(Geojson geojson) {
        this.geojson = geojson;
    }

    @JsonProperty("text_color")
    public String getTextColor() {
        return textColor;
    }

    @JsonProperty("text_color")
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    @JsonProperty("physical_modes")
    public List<PhysicalMode> getPhysicalModes() {
        return physicalModes;
    }

    @JsonProperty("physical_modes")
    public void setPhysicalModes(List<PhysicalMode> physicalModes) {
        this.physicalModes = physicalModes;
    }

    @JsonProperty("codes")
    public List<Object> getCodes() {
        return codes;
    }

    @JsonProperty("codes")
    public void setCodes(List<Object> codes) {
        this.codes = codes;
    }

    @JsonProperty("closing_time")
    public String getClosingTime() {
        return closingTime;
    }

    @JsonProperty("closing_time")
    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    @JsonProperty("opening_time")
    public String getOpeningTime() {
        return openingTime;
    }

    @JsonProperty("opening_time")
    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    @JsonProperty("commercial_mode")
    public CommercialMode getCommercialMode() {
        return commercialMode;
    }

    @JsonProperty("commercial_mode")
    public void setCommercialMode(CommercialMode commercialMode) {
        this.commercialMode = commercialMode;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
