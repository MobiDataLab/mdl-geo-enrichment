
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
    "name",
    "links",
    "is_frequence",
    "geojson",
    "direction_type",
    "id"
})
@Generated("jsonschema2pojo")
public class Route {

    @JsonProperty("direction")
    private Direction direction;
    @JsonProperty("name")
    private String name;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonProperty("is_frequence")
    private String isFrequence;
    @JsonProperty("geojson")
    private Geojson geojson;
    @JsonProperty("direction_type")
    private String directionType;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("direction")
    public Direction getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("is_frequence")
    public String getIsFrequence() {
        return isFrequence;
    }

    @JsonProperty("is_frequence")
    public void setIsFrequence(String isFrequence) {
        this.isFrequence = isFrequence;
    }

    @JsonProperty("geojson")
    public Geojson getGeojson() {
        return geojson;
    }

    @JsonProperty("geojson")
    public void setGeojson(Geojson geojson) {
        this.geojson = geojson;
    }

    @JsonProperty("direction_type")
    public String getDirectionType() {
        return directionType;
    }

    @JsonProperty("direction_type")
    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
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
