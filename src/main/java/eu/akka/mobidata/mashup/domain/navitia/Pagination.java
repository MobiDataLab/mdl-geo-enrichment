
package eu.akka.mobidata.mashup.domain.navitia;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "start_page",
        "items_on_page",
        "items_per_page",
        "total_result"
})
@Generated("jsonschema2pojo")
public class Pagination {

    @JsonProperty("start_page")
    private Integer startPage;
    @JsonProperty("items_on_page")
    private Integer itemsOnPage;
    @JsonProperty("items_per_page")
    private Integer itemsPerPage;
    @JsonProperty("total_result")
    private Integer totalResult;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("start_page")
    public Integer getStartPage() {
        return startPage;
    }

    @JsonProperty("start_page")
    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    @JsonProperty("items_on_page")
    public Integer getItemsOnPage() {
        return itemsOnPage;
    }

    @JsonProperty("items_on_page")
    public void setItemsOnPage(Integer itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    @JsonProperty("items_per_page")
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    @JsonProperty("items_per_page")
    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    @JsonProperty("total_result")
    public Integer getTotalResult() {
        return totalResult;
    }

    @JsonProperty("total_result")
    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
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
