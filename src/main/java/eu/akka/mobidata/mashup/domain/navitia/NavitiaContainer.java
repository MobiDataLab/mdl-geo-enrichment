
package eu.akka.mobidata.mashup.domain.navitia;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "tickets",
        "terminus",
        "links",
        "journeys",
        "disruptions",
        "notes",
        "lines",
        "feed_publishers",
        "context",
        "exceptions"
})
@Generated("jsonschema2pojo")
public class NavitiaContainer {

    @JsonProperty("pagination")
    private Pagination pagination;
    @JsonProperty("tickets")
    private List<Object> tickets = null;
    @JsonProperty("terminus")
    private List<Terminu> terminus = null;
    @JsonProperty("links")
    private List<Link> links = null;
    @JsonProperty("journeys")
    private List<Journey> journeys = null;
    @JsonProperty("disruptions")
    private List<Object> disruptions = null;
    @JsonProperty("notes")
    private List<Object> notes = null;
    @JsonProperty("lines")
    private List<Line> lines = null;
    @JsonProperty("feed_publishers")
    private List<FeedPublisher> feedPublishers = null;
    @JsonProperty("context")
    private Context context;
    @JsonProperty("exceptions")
    private List<Object> exceptions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tickets")
    public List<Object> getTickets() {
        return tickets;
    }

    @JsonProperty("tickets")
    public void setTickets(List<Object> tickets) {
        this.tickets = tickets;
    }

    @JsonProperty("terminus")
    public List<Terminu> getTerminus() {
        return terminus;
    }

    @JsonProperty("terminus")
    public void setTerminus(List<Terminu> terminus) {
        this.terminus = terminus;
    }


    @JsonProperty("pagination")
    public Pagination getPagination() {
        return pagination;
    }

    @JsonProperty("pagination")
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonProperty("journeys")
    public List<Journey> getJourneys() {
        return journeys;
    }

    @JsonProperty("journeys")
    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    @JsonProperty("lines")
    public List<Line> getLines() {
        return lines;
    }

    @JsonProperty("lines")
    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    @JsonProperty("disruptions")
    public List<Object> getDisruptions() {
        return disruptions;
    }

    @JsonProperty("disruptions")
    public void setDisruptions(List<Object> disruptions) {
        this.disruptions = disruptions;
    }

    @JsonProperty("notes")
    public List<Object> getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(List<Object> notes) {
        this.notes = notes;
    }

    @JsonProperty("feed_publishers")
    public List<FeedPublisher> getFeedPublishers() {
        return feedPublishers;
    }

    @JsonProperty("feed_publishers")
    public void setFeedPublishers(List<FeedPublisher> feedPublishers) {
        this.feedPublishers = feedPublishers;
    }

    @JsonProperty("context")
    public Context getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(Context context) {
        this.context = context;
    }

    @JsonProperty("exceptions")
    public List<Object> getExceptions() {
        return exceptions;
    }

    @JsonProperty("exceptions")
    public void setExceptions(List<Object> exceptions) {
        this.exceptions = exceptions;
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
