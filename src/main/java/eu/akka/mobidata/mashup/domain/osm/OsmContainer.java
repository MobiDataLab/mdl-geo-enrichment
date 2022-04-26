
package eu.akka.mobidata.mashup.domain.osm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "generator",
    "osm3s",
    "elements"
})
@Generated("jsonschema2pojo")
public class OsmContainer {

    @JsonProperty("version")
    private Double version;
    @JsonProperty("generator")
    private String generator;
    @JsonProperty("osm3s")
    private Osm3s osm3s;
    @JsonProperty("elements")
    private List<Element> elements = new ArrayList<Element>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("version")
    public Double getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Double version) {
        this.version = version;
    }

    public OsmContainer withVersion(Double version) {
        this.version = version;
        return this;
    }

    @JsonProperty("generator")
    public String getGenerator() {
        return generator;
    }

    @JsonProperty("generator")
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public OsmContainer withGenerator(String generator) {
        this.generator = generator;
        return this;
    }

    @JsonProperty("osm3s")
    public Osm3s getOsm3s() {
        return osm3s;
    }

    @JsonProperty("osm3s")
    public void setOsm3s(Osm3s osm3s) {
        this.osm3s = osm3s;
    }

    public OsmContainer withOsm3s(Osm3s osm3s) {
        this.osm3s = osm3s;
        return this;
    }

    @JsonProperty("elements")
    public List<Element> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public OsmContainer withElements(List<Element> elements) {
        this.elements = elements;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OsmContainer withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OsmContainer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("generator");
        sb.append('=');
        sb.append(((this.generator == null)?"<null>":this.generator));
        sb.append(',');
        sb.append("osm3s");
        sb.append('=');
        sb.append(((this.osm3s == null)?"<null>":this.osm3s));
        sb.append(',');
        sb.append("elements");
        sb.append('=');
        sb.append(((this.elements == null)?"<null>":this.elements));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.generator == null)? 0 :this.generator.hashCode()));
        result = ((result* 31)+((this.osm3s == null)? 0 :this.osm3s.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.elements == null)? 0 :this.elements.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OsmContainer) == false) {
            return false;
        }
        OsmContainer rhs = ((OsmContainer) other);
        return ((((((this.generator == rhs.generator)||((this.generator!= null)&&this.generator.equals(rhs.generator)))&&((this.osm3s == rhs.osm3s)||((this.osm3s!= null)&&this.osm3s.equals(rhs.osm3s))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.elements == rhs.elements)||((this.elements!= null)&&this.elements.equals(rhs.elements))));
    }

}
