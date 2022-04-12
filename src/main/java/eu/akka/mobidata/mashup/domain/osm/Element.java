
package eu.akka.mobidata.mashup.domain.osm;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "id",
        "lat",
        "lon",
        "timestamp",
        "version",
        "changeset",
        "user",
        "uid",
        "nodes",
        "tags"
})
@Generated("jsonschema2pojo")
public class Element {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("changeset")
    private Integer changeset;
    @JsonProperty("user")
    private String user;
    @JsonProperty("uid")
    private Integer uid;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("nodes")
    private List<Integer> nodes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("changeset")
    public Integer getChangeset() {
        return changeset;
    }

    @JsonProperty("changeset")
    public void setChangeset(Integer changeset) {
        this.changeset = changeset;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("uid")
    public Integer getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("nodes")
    public List<Integer> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
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
