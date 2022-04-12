
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
    "direction",
    "code",
    "description",
    "links",
    "color",
    "physical_mode",
    "headsign",
    "commercial_mode",
    "equipments",
    "trip_short_name",
    "label",
    "text_color",
    "network",
    "name"
})
@Generated("jsonschema2pojo")
public class DisplayInformations {

    @JsonProperty("direction")
    private String direction;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("links")
    private List<Link> links = null;
    @JsonProperty("color")
    private String color;
    @JsonProperty("physical_mode")
    private String physicalMode;
    @JsonProperty("headsign")
    private String headsign;
    @JsonProperty("commercial_mode")
    private String commercialMode;
    @JsonProperty("equipments")
    private List<String> equipments = null;
    @JsonProperty("trip_short_name")
    private String tripShortName;
    @JsonProperty("label")
    private String label;
    @JsonProperty("text_color")
    private String textColor;
    @JsonProperty("network")
    private String network;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
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

    @JsonProperty("physical_mode")
    public String getPhysicalMode() {
        return physicalMode;
    }

    @JsonProperty("physical_mode")
    public void setPhysicalMode(String physicalMode) {
        this.physicalMode = physicalMode;
    }

    @JsonProperty("headsign")
    public String getHeadsign() {
        return headsign;
    }

    @JsonProperty("headsign")
    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    @JsonProperty("commercial_mode")
    public String getCommercialMode() {
        return commercialMode;
    }

    @JsonProperty("commercial_mode")
    public void setCommercialMode(String commercialMode) {
        this.commercialMode = commercialMode;
    }

    @JsonProperty("equipments")
    public List<String> getEquipments() {
        return equipments;
    }

    @JsonProperty("equipments")
    public void setEquipments(List<String> equipments) {
        this.equipments = equipments;
    }

    @JsonProperty("trip_short_name")
    public String getTripShortName() {
        return tripShortName;
    }

    @JsonProperty("trip_short_name")
    public void setTripShortName(String tripShortName) {
        this.tripShortName = tripShortName;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("text_color")
    public String getTextColor() {
        return textColor;
    }

    @JsonProperty("text_color")
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(String network) {
        this.network = network;
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
