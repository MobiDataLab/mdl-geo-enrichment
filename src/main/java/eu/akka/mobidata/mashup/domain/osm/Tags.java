
package eu.akka.mobidata.mashup.domain.osm;

import java.util.HashMap;
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
    "bus",
    "highway",
    "name",
    "public_transport",
    "ref",
    "ref:FR:Tisseo",
    "shelter",
    "source",
    "source:date",
    "wheelchair",
    "bench",
    "bin",
    "advertising",
    "lit",
    "name:oc",
    "source:name:oc",
    "tactile_paving",
    "ref:FR:aec31",
    "source:date:ref:aec31",
    "source:ref:aec31",
    "alt_name",
    "route_ref",
    "mapillary",
    "image",
    "note",
    "survey:date",
    "passenger_information_display",
    "operator",
    "advertising:animated",
    "advertising:display_surface",
    "advertising:luminous",
    "advertising:sides",
    "advertising:size",
    "advertising:support",
    "departures_board",
    "old_name",
    "description",
    "line",
    "pole",
    "network",
    "name:aec31",
    "fixme",
    "level",
    "source:location",
    "name:FR:aec31",
    "surface",
    "network:wikidata",
    "network:wikipedia",
    "short_name",
    "lanes",
    "source:ref:FR:Tiss\u00e9o",
    "stop_id"
})
@Generated("jsonschema2pojo")
public class Tags {

    @JsonProperty("bus")
    private String bus;
    @JsonProperty("highway")
    private String highway;
    @JsonProperty("name")
    private String name;
    @JsonProperty("public_transport")
    private String publicTransport;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("ref:FR:Tisseo")
    private String refFRTisseo;
    @JsonProperty("shelter")
    private String shelter;
    @JsonProperty("source")
    private String source;
    @JsonProperty("source:date")
    private String sourceDate;
    @JsonProperty("wheelchair")
    private String wheelchair;
    @JsonProperty("bench")
    private String bench;
    @JsonProperty("bin")
    private String bin;
    @JsonProperty("advertising")
    private String advertising;
    @JsonProperty("lit")
    private String lit;
    @JsonProperty("name:oc")
    private String nameOc;
    @JsonProperty("source:name:oc")
    private String sourceNameOc;
    @JsonProperty("tactile_paving")
    private String tactilePaving;
    @JsonProperty("ref:FR:aec31")
    private String refFRAec31;
    @JsonProperty("source:date:ref:aec31")
    private String sourceDateRefAec31;
    @JsonProperty("source:ref:aec31")
    private String sourceRefAec31;
    @JsonProperty("alt_name")
    private String altName;
    @JsonProperty("route_ref")
    private String routeRef;
    @JsonProperty("mapillary")
    private String mapillary;
    @JsonProperty("image")
    private String image;
    @JsonProperty("note")
    private String note;
    @JsonProperty("survey:date")
    private String surveyDate;
    @JsonProperty("passenger_information_display")
    private String passengerInformationDisplay;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("advertising:animated")
    private String advertisingAnimated;
    @JsonProperty("advertising:display_surface")
    private String advertisingDisplaySurface;
    @JsonProperty("advertising:luminous")
    private String advertisingLuminous;
    @JsonProperty("advertising:sides")
    private String advertisingSides;
    @JsonProperty("advertising:size")
    private String advertisingSize;
    @JsonProperty("advertising:support")
    private String advertisingSupport;
    @JsonProperty("departures_board")
    private String departuresBoard;
    @JsonProperty("old_name")
    private String oldName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("line")
    private String line;
    @JsonProperty("pole")
    private String pole;
    @JsonProperty("network")
    private String network;
    @JsonProperty("name:aec31")
    private String nameAec31;
    @JsonProperty("fixme")
    private String fixme;
    @JsonProperty("level")
    private String level;
    @JsonProperty("source:location")
    private String sourceLocation;
    @JsonProperty("name:FR:aec31")
    private String nameFRAec31;
    @JsonProperty("surface")
    private String surface;
    @JsonProperty("network:wikidata")
    private String networkWikidata;
    @JsonProperty("network:wikipedia")
    private String networkWikipedia;
    @JsonProperty("short_name")
    private String shortName;
    @JsonProperty("lanes")
    private String lanes;
    @JsonProperty("source:ref:FR:Tiss\u00e9o")
    private String sourceRefFRTissO;
    @JsonProperty("stop_id")
    private String stopId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bus")
    public String getBus() {
        return bus;
    }

    @JsonProperty("bus")
    public void setBus(String bus) {
        this.bus = bus;
    }

    public Tags withBus(String bus) {
        this.bus = bus;
        return this;
    }

    @JsonProperty("highway")
    public String getHighway() {
        return highway;
    }

    @JsonProperty("highway")
    public void setHighway(String highway) {
        this.highway = highway;
    }

    public Tags withHighway(String highway) {
        this.highway = highway;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Tags withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("public_transport")
    public String getPublicTransport() {
        return publicTransport;
    }

    @JsonProperty("public_transport")
    public void setPublicTransport(String publicTransport) {
        this.publicTransport = publicTransport;
    }

    public Tags withPublicTransport(String publicTransport) {
        this.publicTransport = publicTransport;
        return this;
    }

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    public Tags withRef(String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("ref:FR:Tisseo")
    public String getRefFRTisseo() {
        return refFRTisseo;
    }

    @JsonProperty("ref:FR:Tisseo")
    public void setRefFRTisseo(String refFRTisseo) {
        this.refFRTisseo = refFRTisseo;
    }

    public Tags withRefFRTisseo(String refFRTisseo) {
        this.refFRTisseo = refFRTisseo;
        return this;
    }

    @JsonProperty("shelter")
    public String getShelter() {
        return shelter;
    }

    @JsonProperty("shelter")
    public void setShelter(String shelter) {
        this.shelter = shelter;
    }

    public Tags withShelter(String shelter) {
        this.shelter = shelter;
        return this;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public Tags withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("source:date")
    public String getSourceDate() {
        return sourceDate;
    }

    @JsonProperty("source:date")
    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

    public Tags withSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
        return this;
    }

    @JsonProperty("wheelchair")
    public String getWheelchair() {
        return wheelchair;
    }

    @JsonProperty("wheelchair")
    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    public Tags withWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
        return this;
    }

    @JsonProperty("bench")
    public String getBench() {
        return bench;
    }

    @JsonProperty("bench")
    public void setBench(String bench) {
        this.bench = bench;
    }

    public Tags withBench(String bench) {
        this.bench = bench;
        return this;
    }

    @JsonProperty("bin")
    public String getBin() {
        return bin;
    }

    @JsonProperty("bin")
    public void setBin(String bin) {
        this.bin = bin;
    }

    public Tags withBin(String bin) {
        this.bin = bin;
        return this;
    }

    @JsonProperty("advertising")
    public String getAdvertising() {
        return advertising;
    }

    @JsonProperty("advertising")
    public void setAdvertising(String advertising) {
        this.advertising = advertising;
    }

    public Tags withAdvertising(String advertising) {
        this.advertising = advertising;
        return this;
    }

    @JsonProperty("lit")
    public String getLit() {
        return lit;
    }

    @JsonProperty("lit")
    public void setLit(String lit) {
        this.lit = lit;
    }

    public Tags withLit(String lit) {
        this.lit = lit;
        return this;
    }

    @JsonProperty("name:oc")
    public String getNameOc() {
        return nameOc;
    }

    @JsonProperty("name:oc")
    public void setNameOc(String nameOc) {
        this.nameOc = nameOc;
    }

    public Tags withNameOc(String nameOc) {
        this.nameOc = nameOc;
        return this;
    }

    @JsonProperty("source:name:oc")
    public String getSourceNameOc() {
        return sourceNameOc;
    }

    @JsonProperty("source:name:oc")
    public void setSourceNameOc(String sourceNameOc) {
        this.sourceNameOc = sourceNameOc;
    }

    public Tags withSourceNameOc(String sourceNameOc) {
        this.sourceNameOc = sourceNameOc;
        return this;
    }

    @JsonProperty("tactile_paving")
    public String getTactilePaving() {
        return tactilePaving;
    }

    @JsonProperty("tactile_paving")
    public void setTactilePaving(String tactilePaving) {
        this.tactilePaving = tactilePaving;
    }

    public Tags withTactilePaving(String tactilePaving) {
        this.tactilePaving = tactilePaving;
        return this;
    }

    @JsonProperty("ref:FR:aec31")
    public String getRefFRAec31() {
        return refFRAec31;
    }

    @JsonProperty("ref:FR:aec31")
    public void setRefFRAec31(String refFRAec31) {
        this.refFRAec31 = refFRAec31;
    }

    public Tags withRefFRAec31(String refFRAec31) {
        this.refFRAec31 = refFRAec31;
        return this;
    }

    @JsonProperty("source:date:ref:aec31")
    public String getSourceDateRefAec31() {
        return sourceDateRefAec31;
    }

    @JsonProperty("source:date:ref:aec31")
    public void setSourceDateRefAec31(String sourceDateRefAec31) {
        this.sourceDateRefAec31 = sourceDateRefAec31;
    }

    public Tags withSourceDateRefAec31(String sourceDateRefAec31) {
        this.sourceDateRefAec31 = sourceDateRefAec31;
        return this;
    }

    @JsonProperty("source:ref:aec31")
    public String getSourceRefAec31() {
        return sourceRefAec31;
    }

    @JsonProperty("source:ref:aec31")
    public void setSourceRefAec31(String sourceRefAec31) {
        this.sourceRefAec31 = sourceRefAec31;
    }

    public Tags withSourceRefAec31(String sourceRefAec31) {
        this.sourceRefAec31 = sourceRefAec31;
        return this;
    }

    @JsonProperty("alt_name")
    public String getAltName() {
        return altName;
    }

    @JsonProperty("alt_name")
    public void setAltName(String altName) {
        this.altName = altName;
    }

    public Tags withAltName(String altName) {
        this.altName = altName;
        return this;
    }

    @JsonProperty("route_ref")
    public String getRouteRef() {
        return routeRef;
    }

    @JsonProperty("route_ref")
    public void setRouteRef(String routeRef) {
        this.routeRef = routeRef;
    }

    public Tags withRouteRef(String routeRef) {
        this.routeRef = routeRef;
        return this;
    }

    @JsonProperty("mapillary")
    public String getMapillary() {
        return mapillary;
    }

    @JsonProperty("mapillary")
    public void setMapillary(String mapillary) {
        this.mapillary = mapillary;
    }

    public Tags withMapillary(String mapillary) {
        this.mapillary = mapillary;
        return this;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    public Tags withImage(String image) {
        this.image = image;
        return this;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(String note) {
        this.note = note;
    }

    public Tags withNote(String note) {
        this.note = note;
        return this;
    }

    @JsonProperty("survey:date")
    public String getSurveyDate() {
        return surveyDate;
    }

    @JsonProperty("survey:date")
    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public Tags withSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
        return this;
    }

    @JsonProperty("passenger_information_display")
    public String getPassengerInformationDisplay() {
        return passengerInformationDisplay;
    }

    @JsonProperty("passenger_information_display")
    public void setPassengerInformationDisplay(String passengerInformationDisplay) {
        this.passengerInformationDisplay = passengerInformationDisplay;
    }

    public Tags withPassengerInformationDisplay(String passengerInformationDisplay) {
        this.passengerInformationDisplay = passengerInformationDisplay;
        return this;
    }

    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Tags withOperator(String operator) {
        this.operator = operator;
        return this;
    }

    @JsonProperty("advertising:animated")
    public String getAdvertisingAnimated() {
        return advertisingAnimated;
    }

    @JsonProperty("advertising:animated")
    public void setAdvertisingAnimated(String advertisingAnimated) {
        this.advertisingAnimated = advertisingAnimated;
    }

    public Tags withAdvertisingAnimated(String advertisingAnimated) {
        this.advertisingAnimated = advertisingAnimated;
        return this;
    }

    @JsonProperty("advertising:display_surface")
    public String getAdvertisingDisplaySurface() {
        return advertisingDisplaySurface;
    }

    @JsonProperty("advertising:display_surface")
    public void setAdvertisingDisplaySurface(String advertisingDisplaySurface) {
        this.advertisingDisplaySurface = advertisingDisplaySurface;
    }

    public Tags withAdvertisingDisplaySurface(String advertisingDisplaySurface) {
        this.advertisingDisplaySurface = advertisingDisplaySurface;
        return this;
    }

    @JsonProperty("advertising:luminous")
    public String getAdvertisingLuminous() {
        return advertisingLuminous;
    }

    @JsonProperty("advertising:luminous")
    public void setAdvertisingLuminous(String advertisingLuminous) {
        this.advertisingLuminous = advertisingLuminous;
    }

    public Tags withAdvertisingLuminous(String advertisingLuminous) {
        this.advertisingLuminous = advertisingLuminous;
        return this;
    }

    @JsonProperty("advertising:sides")
    public String getAdvertisingSides() {
        return advertisingSides;
    }

    @JsonProperty("advertising:sides")
    public void setAdvertisingSides(String advertisingSides) {
        this.advertisingSides = advertisingSides;
    }

    public Tags withAdvertisingSides(String advertisingSides) {
        this.advertisingSides = advertisingSides;
        return this;
    }

    @JsonProperty("advertising:size")
    public String getAdvertisingSize() {
        return advertisingSize;
    }

    @JsonProperty("advertising:size")
    public void setAdvertisingSize(String advertisingSize) {
        this.advertisingSize = advertisingSize;
    }

    public Tags withAdvertisingSize(String advertisingSize) {
        this.advertisingSize = advertisingSize;
        return this;
    }

    @JsonProperty("advertising:support")
    public String getAdvertisingSupport() {
        return advertisingSupport;
    }

    @JsonProperty("advertising:support")
    public void setAdvertisingSupport(String advertisingSupport) {
        this.advertisingSupport = advertisingSupport;
    }

    public Tags withAdvertisingSupport(String advertisingSupport) {
        this.advertisingSupport = advertisingSupport;
        return this;
    }

    @JsonProperty("departures_board")
    public String getDeparturesBoard() {
        return departuresBoard;
    }

    @JsonProperty("departures_board")
    public void setDeparturesBoard(String departuresBoard) {
        this.departuresBoard = departuresBoard;
    }

    public Tags withDeparturesBoard(String departuresBoard) {
        this.departuresBoard = departuresBoard;
        return this;
    }

    @JsonProperty("old_name")
    public String getOldName() {
        return oldName;
    }

    @JsonProperty("old_name")
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Tags withOldName(String oldName) {
        this.oldName = oldName;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Tags withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("line")
    public String getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(String line) {
        this.line = line;
    }

    public Tags withLine(String line) {
        this.line = line;
        return this;
    }

    @JsonProperty("pole")
    public String getPole() {
        return pole;
    }

    @JsonProperty("pole")
    public void setPole(String pole) {
        this.pole = pole;
    }

    public Tags withPole(String pole) {
        this.pole = pole;
        return this;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(String network) {
        this.network = network;
    }

    public Tags withNetwork(String network) {
        this.network = network;
        return this;
    }

    @JsonProperty("name:aec31")
    public String getNameAec31() {
        return nameAec31;
    }

    @JsonProperty("name:aec31")
    public void setNameAec31(String nameAec31) {
        this.nameAec31 = nameAec31;
    }

    public Tags withNameAec31(String nameAec31) {
        this.nameAec31 = nameAec31;
        return this;
    }

    @JsonProperty("fixme")
    public String getFixme() {
        return fixme;
    }

    @JsonProperty("fixme")
    public void setFixme(String fixme) {
        this.fixme = fixme;
    }

    public Tags withFixme(String fixme) {
        this.fixme = fixme;
        return this;
    }

    @JsonProperty("level")
    public String getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(String level) {
        this.level = level;
    }

    public Tags withLevel(String level) {
        this.level = level;
        return this;
    }

    @JsonProperty("source:location")
    public String getSourceLocation() {
        return sourceLocation;
    }

    @JsonProperty("source:location")
    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public Tags withSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
        return this;
    }

    @JsonProperty("name:FR:aec31")
    public String getNameFRAec31() {
        return nameFRAec31;
    }

    @JsonProperty("name:FR:aec31")
    public void setNameFRAec31(String nameFRAec31) {
        this.nameFRAec31 = nameFRAec31;
    }

    public Tags withNameFRAec31(String nameFRAec31) {
        this.nameFRAec31 = nameFRAec31;
        return this;
    }

    @JsonProperty("surface")
    public String getSurface() {
        return surface;
    }

    @JsonProperty("surface")
    public void setSurface(String surface) {
        this.surface = surface;
    }

    public Tags withSurface(String surface) {
        this.surface = surface;
        return this;
    }

    @JsonProperty("network:wikidata")
    public String getNetworkWikidata() {
        return networkWikidata;
    }

    @JsonProperty("network:wikidata")
    public void setNetworkWikidata(String networkWikidata) {
        this.networkWikidata = networkWikidata;
    }

    public Tags withNetworkWikidata(String networkWikidata) {
        this.networkWikidata = networkWikidata;
        return this;
    }

    @JsonProperty("network:wikipedia")
    public String getNetworkWikipedia() {
        return networkWikipedia;
    }

    @JsonProperty("network:wikipedia")
    public void setNetworkWikipedia(String networkWikipedia) {
        this.networkWikipedia = networkWikipedia;
    }

    public Tags withNetworkWikipedia(String networkWikipedia) {
        this.networkWikipedia = networkWikipedia;
        return this;
    }

    @JsonProperty("short_name")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("short_name")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Tags withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    @JsonProperty("lanes")
    public String getLanes() {
        return lanes;
    }

    @JsonProperty("lanes")
    public void setLanes(String lanes) {
        this.lanes = lanes;
    }

    public Tags withLanes(String lanes) {
        this.lanes = lanes;
        return this;
    }

    @JsonProperty("source:ref:FR:Tiss\u00e9o")
    public String getSourceRefFRTissO() {
        return sourceRefFRTissO;
    }

    @JsonProperty("source:ref:FR:Tiss\u00e9o")
    public void setSourceRefFRTissO(String sourceRefFRTissO) {
        this.sourceRefFRTissO = sourceRefFRTissO;
    }

    public Tags withSourceRefFRTissO(String sourceRefFRTissO) {
        this.sourceRefFRTissO = sourceRefFRTissO;
        return this;
    }

    @JsonProperty("stop_id")
    public String getStopId() {
        return stopId;
    }

    @JsonProperty("stop_id")
    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public Tags withStopId(String stopId) {
        this.stopId = stopId;
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

    public Tags withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Tags.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bus");
        sb.append('=');
        sb.append(((this.bus == null)?"<null>":this.bus));
        sb.append(',');
        sb.append("highway");
        sb.append('=');
        sb.append(((this.highway == null)?"<null>":this.highway));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("publicTransport");
        sb.append('=');
        sb.append(((this.publicTransport == null)?"<null>":this.publicTransport));
        sb.append(',');
        sb.append("ref");
        sb.append('=');
        sb.append(((this.ref == null)?"<null>":this.ref));
        sb.append(',');
        sb.append("refFRTisseo");
        sb.append('=');
        sb.append(((this.refFRTisseo == null)?"<null>":this.refFRTisseo));
        sb.append(',');
        sb.append("shelter");
        sb.append('=');
        sb.append(((this.shelter == null)?"<null>":this.shelter));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("sourceDate");
        sb.append('=');
        sb.append(((this.sourceDate == null)?"<null>":this.sourceDate));
        sb.append(',');
        sb.append("wheelchair");
        sb.append('=');
        sb.append(((this.wheelchair == null)?"<null>":this.wheelchair));
        sb.append(',');
        sb.append("bench");
        sb.append('=');
        sb.append(((this.bench == null)?"<null>":this.bench));
        sb.append(',');
        sb.append("bin");
        sb.append('=');
        sb.append(((this.bin == null)?"<null>":this.bin));
        sb.append(',');
        sb.append("advertising");
        sb.append('=');
        sb.append(((this.advertising == null)?"<null>":this.advertising));
        sb.append(',');
        sb.append("lit");
        sb.append('=');
        sb.append(((this.lit == null)?"<null>":this.lit));
        sb.append(',');
        sb.append("nameOc");
        sb.append('=');
        sb.append(((this.nameOc == null)?"<null>":this.nameOc));
        sb.append(',');
        sb.append("sourceNameOc");
        sb.append('=');
        sb.append(((this.sourceNameOc == null)?"<null>":this.sourceNameOc));
        sb.append(',');
        sb.append("tactilePaving");
        sb.append('=');
        sb.append(((this.tactilePaving == null)?"<null>":this.tactilePaving));
        sb.append(',');
        sb.append("refFRAec31");
        sb.append('=');
        sb.append(((this.refFRAec31 == null)?"<null>":this.refFRAec31));
        sb.append(',');
        sb.append("sourceDateRefAec31");
        sb.append('=');
        sb.append(((this.sourceDateRefAec31 == null)?"<null>":this.sourceDateRefAec31));
        sb.append(',');
        sb.append("sourceRefAec31");
        sb.append('=');
        sb.append(((this.sourceRefAec31 == null)?"<null>":this.sourceRefAec31));
        sb.append(',');
        sb.append("altName");
        sb.append('=');
        sb.append(((this.altName == null)?"<null>":this.altName));
        sb.append(',');
        sb.append("routeRef");
        sb.append('=');
        sb.append(((this.routeRef == null)?"<null>":this.routeRef));
        sb.append(',');
        sb.append("mapillary");
        sb.append('=');
        sb.append(((this.mapillary == null)?"<null>":this.mapillary));
        sb.append(',');
        sb.append("image");
        sb.append('=');
        sb.append(((this.image == null)?"<null>":this.image));
        sb.append(',');
        sb.append("note");
        sb.append('=');
        sb.append(((this.note == null)?"<null>":this.note));
        sb.append(',');
        sb.append("surveyDate");
        sb.append('=');
        sb.append(((this.surveyDate == null)?"<null>":this.surveyDate));
        sb.append(',');
        sb.append("passengerInformationDisplay");
        sb.append('=');
        sb.append(((this.passengerInformationDisplay == null)?"<null>":this.passengerInformationDisplay));
        sb.append(',');
        sb.append("operator");
        sb.append('=');
        sb.append(((this.operator == null)?"<null>":this.operator));
        sb.append(',');
        sb.append("advertisingAnimated");
        sb.append('=');
        sb.append(((this.advertisingAnimated == null)?"<null>":this.advertisingAnimated));
        sb.append(',');
        sb.append("advertisingDisplaySurface");
        sb.append('=');
        sb.append(((this.advertisingDisplaySurface == null)?"<null>":this.advertisingDisplaySurface));
        sb.append(',');
        sb.append("advertisingLuminous");
        sb.append('=');
        sb.append(((this.advertisingLuminous == null)?"<null>":this.advertisingLuminous));
        sb.append(',');
        sb.append("advertisingSides");
        sb.append('=');
        sb.append(((this.advertisingSides == null)?"<null>":this.advertisingSides));
        sb.append(',');
        sb.append("advertisingSize");
        sb.append('=');
        sb.append(((this.advertisingSize == null)?"<null>":this.advertisingSize));
        sb.append(',');
        sb.append("advertisingSupport");
        sb.append('=');
        sb.append(((this.advertisingSupport == null)?"<null>":this.advertisingSupport));
        sb.append(',');
        sb.append("departuresBoard");
        sb.append('=');
        sb.append(((this.departuresBoard == null)?"<null>":this.departuresBoard));
        sb.append(',');
        sb.append("oldName");
        sb.append('=');
        sb.append(((this.oldName == null)?"<null>":this.oldName));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("line");
        sb.append('=');
        sb.append(((this.line == null)?"<null>":this.line));
        sb.append(',');
        sb.append("pole");
        sb.append('=');
        sb.append(((this.pole == null)?"<null>":this.pole));
        sb.append(',');
        sb.append("network");
        sb.append('=');
        sb.append(((this.network == null)?"<null>":this.network));
        sb.append(',');
        sb.append("nameAec31");
        sb.append('=');
        sb.append(((this.nameAec31 == null)?"<null>":this.nameAec31));
        sb.append(',');
        sb.append("fixme");
        sb.append('=');
        sb.append(((this.fixme == null)?"<null>":this.fixme));
        sb.append(',');
        sb.append("level");
        sb.append('=');
        sb.append(((this.level == null)?"<null>":this.level));
        sb.append(',');
        sb.append("sourceLocation");
        sb.append('=');
        sb.append(((this.sourceLocation == null)?"<null>":this.sourceLocation));
        sb.append(',');
        sb.append("nameFRAec31");
        sb.append('=');
        sb.append(((this.nameFRAec31 == null)?"<null>":this.nameFRAec31));
        sb.append(',');
        sb.append("surface");
        sb.append('=');
        sb.append(((this.surface == null)?"<null>":this.surface));
        sb.append(',');
        sb.append("networkWikidata");
        sb.append('=');
        sb.append(((this.networkWikidata == null)?"<null>":this.networkWikidata));
        sb.append(',');
        sb.append("networkWikipedia");
        sb.append('=');
        sb.append(((this.networkWikipedia == null)?"<null>":this.networkWikipedia));
        sb.append(',');
        sb.append("shortName");
        sb.append('=');
        sb.append(((this.shortName == null)?"<null>":this.shortName));
        sb.append(',');
        sb.append("lanes");
        sb.append('=');
        sb.append(((this.lanes == null)?"<null>":this.lanes));
        sb.append(',');
        sb.append("sourceRefFRTissO");
        sb.append('=');
        sb.append(((this.sourceRefFRTissO == null)?"<null>":this.sourceRefFRTissO));
        sb.append(',');
        sb.append("stopId");
        sb.append('=');
        sb.append(((this.stopId == null)?"<null>":this.stopId));
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
        result = ((result* 31)+((this.nameOc == null)? 0 :this.nameOc.hashCode()));
        result = ((result* 31)+((this.bus == null)? 0 :this.bus.hashCode()));
        result = ((result* 31)+((this.bin == null)? 0 :this.bin.hashCode()));
        result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
        result = ((result* 31)+((this.advertising == null)? 0 :this.advertising.hashCode()));
        result = ((result* 31)+((this.pole == null)? 0 :this.pole.hashCode()));
        result = ((result* 31)+((this.operator == null)? 0 :this.operator.hashCode()));
        result = ((result* 31)+((this.ref == null)? 0 :this.ref.hashCode()));
        result = ((result* 31)+((this.lit == null)? 0 :this.lit.hashCode()));
        result = ((result* 31)+((this.oldName == null)? 0 :this.oldName.hashCode()));
        result = ((result* 31)+((this.lanes == null)? 0 :this.lanes.hashCode()));
        result = ((result* 31)+((this.advertisingAnimated == null)? 0 :this.advertisingAnimated.hashCode()));
        result = ((result* 31)+((this.highway == null)? 0 :this.highway.hashCode()));
        result = ((result* 31)+((this.networkWikidata == null)? 0 :this.networkWikidata.hashCode()));
        result = ((result* 31)+((this.publicTransport == null)? 0 :this.publicTransport.hashCode()));
        result = ((result* 31)+((this.image == null)? 0 :this.image.hashCode()));
        result = ((result* 31)+((this.fixme == null)? 0 :this.fixme.hashCode()));
        result = ((result* 31)+((this.bench == null)? 0 :this.bench.hashCode()));
        result = ((result* 31)+((this.surface == null)? 0 :this.surface.hashCode()));
        result = ((result* 31)+((this.level == null)? 0 :this.level.hashCode()));
        result = ((result* 31)+((this.nameFRAec31 == null)? 0 :this.nameFRAec31 .hashCode()));
        result = ((result* 31)+((this.sourceNameOc == null)? 0 :this.sourceNameOc.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.shortName == null)? 0 :this.shortName.hashCode()));
        result = ((result* 31)+((this.networkWikipedia == null)? 0 :this.networkWikipedia.hashCode()));
        result = ((result* 31)+((this.wheelchair == null)? 0 :this.wheelchair.hashCode()));
        result = ((result* 31)+((this.note == null)? 0 :this.note.hashCode()));
        result = ((result* 31)+((this.shelter == null)? 0 :this.shelter.hashCode()));
        result = ((result* 31)+((this.refFRTisseo == null)? 0 :this.refFRTisseo.hashCode()));
        result = ((result* 31)+((this.line == null)? 0 :this.line.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.advertisingSize == null)? 0 :this.advertisingSize.hashCode()));
        result = ((result* 31)+((this.departuresBoard == null)? 0 :this.departuresBoard.hashCode()));
        result = ((result* 31)+((this.sourceRefFRTissO == null)? 0 :this.sourceRefFRTissO.hashCode()));
        result = ((result* 31)+((this.network == null)? 0 :this.network.hashCode()));
        result = ((result* 31)+((this.nameAec31 == null)? 0 :this.nameAec31 .hashCode()));
        result = ((result* 31)+((this.sourceDate == null)? 0 :this.sourceDate.hashCode()));
        result = ((result* 31)+((this.routeRef == null)? 0 :this.routeRef.hashCode()));
        result = ((result* 31)+((this.altName == null)? 0 :this.altName.hashCode()));
        result = ((result* 31)+((this.advertisingLuminous == null)? 0 :this.advertisingLuminous.hashCode()));
        result = ((result* 31)+((this.tactilePaving == null)? 0 :this.tactilePaving.hashCode()));
        result = ((result* 31)+((this.sourceRefAec31 == null)? 0 :this.sourceRefAec31 .hashCode()));
        result = ((result* 31)+((this.surveyDate == null)? 0 :this.surveyDate.hashCode()));
        result = ((result* 31)+((this.stopId == null)? 0 :this.stopId.hashCode()));
        result = ((result* 31)+((this.advertisingDisplaySurface == null)? 0 :this.advertisingDisplaySurface.hashCode()));
        result = ((result* 31)+((this.refFRAec31 == null)? 0 :this.refFRAec31 .hashCode()));
        result = ((result* 31)+((this.passengerInformationDisplay == null)? 0 :this.passengerInformationDisplay.hashCode()));
        result = ((result* 31)+((this.advertisingSides == null)? 0 :this.advertisingSides.hashCode()));
        result = ((result* 31)+((this.sourceLocation == null)? 0 :this.sourceLocation.hashCode()));
        result = ((result* 31)+((this.mapillary == null)? 0 :this.mapillary.hashCode()));
        result = ((result* 31)+((this.advertisingSupport == null)? 0 :this.advertisingSupport.hashCode()));
        result = ((result* 31)+((this.sourceDateRefAec31 == null)? 0 :this.sourceDateRefAec31 .hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Tags) == false) {
            return false;
        }
        Tags rhs = ((Tags) other);
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((this.nameOc == rhs.nameOc)||((this.nameOc!= null)&&this.nameOc.equals(rhs.nameOc)))&&((this.bus == rhs.bus)||((this.bus!= null)&&this.bus.equals(rhs.bus))))&&((this.bin == rhs.bin)||((this.bin!= null)&&this.bin.equals(rhs.bin))))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.advertising == rhs.advertising)||((this.advertising!= null)&&this.advertising.equals(rhs.advertising))))&&((this.pole == rhs.pole)||((this.pole!= null)&&this.pole.equals(rhs.pole))))&&((this.operator == rhs.operator)||((this.operator!= null)&&this.operator.equals(rhs.operator))))&&((this.ref == rhs.ref)||((this.ref!= null)&&this.ref.equals(rhs.ref))))&&((this.lit == rhs.lit)||((this.lit!= null)&&this.lit.equals(rhs.lit))))&&((this.oldName == rhs.oldName)||((this.oldName!= null)&&this.oldName.equals(rhs.oldName))))&&((this.lanes == rhs.lanes)||((this.lanes!= null)&&this.lanes.equals(rhs.lanes))))&&((this.advertisingAnimated == rhs.advertisingAnimated)||((this.advertisingAnimated!= null)&&this.advertisingAnimated.equals(rhs.advertisingAnimated))))&&((this.highway == rhs.highway)||((this.highway!= null)&&this.highway.equals(rhs.highway))))&&((this.networkWikidata == rhs.networkWikidata)||((this.networkWikidata!= null)&&this.networkWikidata.equals(rhs.networkWikidata))))&&((this.publicTransport == rhs.publicTransport)||((this.publicTransport!= null)&&this.publicTransport.equals(rhs.publicTransport))))&&((this.image == rhs.image)||((this.image!= null)&&this.image.equals(rhs.image))))&&((this.fixme == rhs.fixme)||((this.fixme!= null)&&this.fixme.equals(rhs.fixme))))&&((this.bench == rhs.bench)||((this.bench!= null)&&this.bench.equals(rhs.bench))))&&((this.surface == rhs.surface)||((this.surface!= null)&&this.surface.equals(rhs.surface))))&&((this.level == rhs.level)||((this.level!= null)&&this.level.equals(rhs.level))))&&((this.nameFRAec31 == rhs.nameFRAec31)||((this.nameFRAec31 != null)&&this.nameFRAec31 .equals(rhs.nameFRAec31))))&&((this.sourceNameOc == rhs.sourceNameOc)||((this.sourceNameOc!= null)&&this.sourceNameOc.equals(rhs.sourceNameOc))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.shortName == rhs.shortName)||((this.shortName!= null)&&this.shortName.equals(rhs.shortName))))&&((this.networkWikipedia == rhs.networkWikipedia)||((this.networkWikipedia!= null)&&this.networkWikipedia.equals(rhs.networkWikipedia))))&&((this.wheelchair == rhs.wheelchair)||((this.wheelchair!= null)&&this.wheelchair.equals(rhs.wheelchair))))&&((this.note == rhs.note)||((this.note!= null)&&this.note.equals(rhs.note))))&&((this.shelter == rhs.shelter)||((this.shelter!= null)&&this.shelter.equals(rhs.shelter))))&&((this.refFRTisseo == rhs.refFRTisseo)||((this.refFRTisseo!= null)&&this.refFRTisseo.equals(rhs.refFRTisseo))))&&((this.line == rhs.line)||((this.line!= null)&&this.line.equals(rhs.line))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.advertisingSize == rhs.advertisingSize)||((this.advertisingSize!= null)&&this.advertisingSize.equals(rhs.advertisingSize))))&&((this.departuresBoard == rhs.departuresBoard)||((this.departuresBoard!= null)&&this.departuresBoard.equals(rhs.departuresBoard))))&&((this.sourceRefFRTissO == rhs.sourceRefFRTissO)||((this.sourceRefFRTissO!= null)&&this.sourceRefFRTissO.equals(rhs.sourceRefFRTissO))))&&((this.network == rhs.network)||((this.network!= null)&&this.network.equals(rhs.network))))&&((this.nameAec31 == rhs.nameAec31)||((this.nameAec31 != null)&&this.nameAec31 .equals(rhs.nameAec31))))&&((this.sourceDate == rhs.sourceDate)||((this.sourceDate!= null)&&this.sourceDate.equals(rhs.sourceDate))))&&((this.routeRef == rhs.routeRef)||((this.routeRef!= null)&&this.routeRef.equals(rhs.routeRef))))&&((this.altName == rhs.altName)||((this.altName!= null)&&this.altName.equals(rhs.altName))))&&((this.advertisingLuminous == rhs.advertisingLuminous)||((this.advertisingLuminous!= null)&&this.advertisingLuminous.equals(rhs.advertisingLuminous))))&&((this.tactilePaving == rhs.tactilePaving)||((this.tactilePaving!= null)&&this.tactilePaving.equals(rhs.tactilePaving))))&&((this.sourceRefAec31 == rhs.sourceRefAec31)||((this.sourceRefAec31 != null)&&this.sourceRefAec31 .equals(rhs.sourceRefAec31))))&&((this.surveyDate == rhs.surveyDate)||((this.surveyDate!= null)&&this.surveyDate.equals(rhs.surveyDate))))&&((this.stopId == rhs.stopId)||((this.stopId!= null)&&this.stopId.equals(rhs.stopId))))&&((this.advertisingDisplaySurface == rhs.advertisingDisplaySurface)||((this.advertisingDisplaySurface!= null)&&this.advertisingDisplaySurface.equals(rhs.advertisingDisplaySurface))))&&((this.refFRAec31 == rhs.refFRAec31)||((this.refFRAec31 != null)&&this.refFRAec31 .equals(rhs.refFRAec31))))&&((this.passengerInformationDisplay == rhs.passengerInformationDisplay)||((this.passengerInformationDisplay!= null)&&this.passengerInformationDisplay.equals(rhs.passengerInformationDisplay))))&&((this.advertisingSides == rhs.advertisingSides)||((this.advertisingSides!= null)&&this.advertisingSides.equals(rhs.advertisingSides))))&&((this.sourceLocation == rhs.sourceLocation)||((this.sourceLocation!= null)&&this.sourceLocation.equals(rhs.sourceLocation))))&&((this.mapillary == rhs.mapillary)||((this.mapillary!= null)&&this.mapillary.equals(rhs.mapillary))))&&((this.advertisingSupport == rhs.advertisingSupport)||((this.advertisingSupport!= null)&&this.advertisingSupport.equals(rhs.advertisingSupport))))&&((this.sourceDateRefAec31 == rhs.sourceDateRefAec31)||((this.sourceDateRefAec31 != null)&&this.sourceDateRefAec31 .equals(rhs.sourceDateRefAec31))));
    }

}
