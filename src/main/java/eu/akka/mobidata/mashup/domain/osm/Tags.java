
package eu.akka.mobidata.mashup.domain.osm;

import java.util.HashMap;
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
    "name",
    "name:oc",
    "public_transport",
    "railway",
    "subway",
    "bus",
    "man_made",
    "operator",
    "station",
    "wikidata",
    "wikipedia",
    "wheelchair",
    "train",
    "search_name",
    "alt_name",
    "cycleway:both",
    "highway",
    "lanes",
    "lit",
    "maxspeed",
    "sidewalk",
    "surface",
    "disabled",
    "emergency",
    "motor_vehicle",
    "oneway",
    "psv",
    "source:maxspeed",
    "taxi",
    "electrified",
    "frequency",
    "gauge",
    "usage",
    "voltage",
    "service",
    "bridge",
    "layer",
    "tunnel",
    "parking:lane:both",
    "cycleway:right",
    "is_in:city",
    "trolley_wire",
    "level",
    "covered",
    "lanes:backward",
    "lanes:forward",
    "lanes:psv:backward",
    "lanes:psv:forward",
    "bicycle",
    "cycleway",
    "foot",
    "oneway:psv",
    "motorcycle",
    "parking:lane:left",
    "parking:lane:right",
    "oneway:motor_vehicle",
    "disabled:conditional",
    "incline:forward",
    "psv:conditional",
    "fixme",
    "admin_level",
    "boundary",
    "name:am",
    "name:ar",
    "name:az",
    "name:be",
    "name:bg",
    "name:bo",
    "name:br",
    "name:ca",
    "name:ce",
    "name:co",
    "name:csb",
    "name:de",
    "name:el",
    "name:eo",
    "name:es",
    "name:eu",
    "name:fa",
    "name:frp",
    "name:gl",
    "name:he",
    "name:hi",
    "name:hy",
    "name:it",
    "name:ja",
    "name:ka",
    "name:kk",
    "name:kn",
    "name:ko",
    "name:la",
    "name:lt",
    "name:lv",
    "name:mhr",
    "name:ml",
    "name:mr",
    "name:mzn",
    "name:nap",
    "name:os",
    "name:pa",
    "name:pl",
    "name:pms",
    "name:pnb",
    "name:ru",
    "name:sc",
    "name:scn",
    "name:sr",
    "name:ta",
    "name:th",
    "name:tt",
    "name:uk",
    "name:ur",
    "name:vec",
    "name:xmf",
    "name:yi",
    "name:yue",
    "name:zh",
    "population",
    "population:date",
    "postal_code",
    "ref:FR:SIREN",
    "ref:INSEE",
    "short_name:ca",
    "short_name:eu",
    "source:name:oc",
    "source:population",
    "source:postal_code",
    "type",
    "website"
})
@Generated("jsonschema2pojo")
public class Tags {

    @JsonProperty("name")
    private String name;
    @JsonProperty("name:oc")
    private String nameOc;
    @JsonProperty("public_transport")
    private String publicTransport;
    @JsonProperty("railway")
    private String railway;
    @JsonProperty("subway")
    private String subway;
    @JsonProperty("bus")
    private String bus;
    @JsonProperty("man_made")
    private String manMade;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("station")
    private String station;
    @JsonProperty("wikidata")
    private String wikidata;
    @JsonProperty("wikipedia")
    private String wikipedia;
    @JsonProperty("wheelchair")
    private String wheelchair;
    @JsonProperty("train")
    private String train;
    @JsonProperty("search_name")
    private String searchName;
    @JsonProperty("alt_name")
    private String altName;
    @JsonProperty("cycleway:both")
    private String cyclewayBoth;
    @JsonProperty("highway")
    private String highway;
    @JsonProperty("lanes")
    private String lanes;
    @JsonProperty("lit")
    private String lit;
    @JsonProperty("maxspeed")
    private String maxspeed;
    @JsonProperty("sidewalk")
    private String sidewalk;
    @JsonProperty("surface")
    private String surface;
    @JsonProperty("disabled")
    private String disabled;
    @JsonProperty("emergency")
    private String emergency;
    @JsonProperty("motor_vehicle")
    private String motorVehicle;
    @JsonProperty("oneway")
    private String oneway;
    @JsonProperty("psv")
    private String psv;
    @JsonProperty("source:maxspeed")
    private String sourceMaxspeed;
    @JsonProperty("taxi")
    private String taxi;
    @JsonProperty("electrified")
    private String electrified;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("gauge")
    private String gauge;
    @JsonProperty("usage")
    private String usage;
    @JsonProperty("voltage")
    private String voltage;
    @JsonProperty("service")
    private String service;
    @JsonProperty("bridge")
    private String bridge;
    @JsonProperty("layer")
    private String layer;
    @JsonProperty("tunnel")
    private String tunnel;
    @JsonProperty("parking:lane:both")
    private String parkingLaneBoth;
    @JsonProperty("cycleway:right")
    private String cyclewayRight;
    @JsonProperty("is_in:city")
    private String isInCity;
    @JsonProperty("trolley_wire")
    private String trolleyWire;
    @JsonProperty("level")
    private String level;
    @JsonProperty("covered")
    private String covered;
    @JsonProperty("lanes:backward")
    private String lanesBackward;
    @JsonProperty("lanes:forward")
    private String lanesForward;
    @JsonProperty("lanes:psv:backward")
    private String lanesPsvBackward;
    @JsonProperty("lanes:psv:forward")
    private String lanesPsvForward;
    @JsonProperty("bicycle")
    private String bicycle;
    @JsonProperty("cycleway")
    private String cycleway;
    @JsonProperty("foot")
    private String foot;
    @JsonProperty("oneway:psv")
    private String onewayPsv;
    @JsonProperty("motorcycle")
    private String motorcycle;
    @JsonProperty("parking:lane:left")
    private String parkingLaneLeft;
    @JsonProperty("parking:lane:right")
    private String parkingLaneRight;
    @JsonProperty("oneway:motor_vehicle")
    private String onewayMotorVehicle;
    @JsonProperty("disabled:conditional")
    private String disabledConditional;
    @JsonProperty("incline:forward")
    private String inclineForward;
    @JsonProperty("psv:conditional")
    private String psvConditional;
    @JsonProperty("fixme")
    private String fixme;
    @JsonProperty("admin_level")
    private String adminLevel;
    @JsonProperty("boundary")
    private String boundary;
    @JsonProperty("name:am")
    private String nameAm;
    @JsonProperty("name:ar")
    private String nameAr;
    @JsonProperty("name:az")
    private String nameAz;
    @JsonProperty("name:be")
    private String nameBe;
    @JsonProperty("name:bg")
    private String nameBg;
    @JsonProperty("name:bo")
    private String nameBo;
    @JsonProperty("name:br")
    private String nameBr;
    @JsonProperty("name:ca")
    private String nameCa;
    @JsonProperty("name:ce")
    private String nameCe;
    @JsonProperty("name:co")
    private String nameCo;
    @JsonProperty("name:csb")
    private String nameCsb;
    @JsonProperty("name:de")
    private String nameDe;
    @JsonProperty("name:el")
    private String nameEl;
    @JsonProperty("name:eo")
    private String nameEo;
    @JsonProperty("name:es")
    private String nameEs;
    @JsonProperty("name:eu")
    private String nameEu;
    @JsonProperty("name:fa")
    private String nameFa;
    @JsonProperty("name:frp")
    private String nameFrp;
    @JsonProperty("name:gl")
    private String nameGl;
    @JsonProperty("name:he")
    private String nameHe;
    @JsonProperty("name:hi")
    private String nameHi;
    @JsonProperty("name:hy")
    private String nameHy;
    @JsonProperty("name:it")
    private String nameIt;
    @JsonProperty("name:ja")
    private String nameJa;
    @JsonProperty("name:ka")
    private String nameKa;
    @JsonProperty("name:kk")
    private String nameKk;
    @JsonProperty("name:kn")
    private String nameKn;
    @JsonProperty("name:ko")
    private String nameKo;
    @JsonProperty("name:la")
    private String nameLa;
    @JsonProperty("name:lt")
    private String nameLt;
    @JsonProperty("name:lv")
    private String nameLv;
    @JsonProperty("name:mhr")
    private String nameMhr;
    @JsonProperty("name:ml")
    private String nameMl;
    @JsonProperty("name:mr")
    private String nameMr;
    @JsonProperty("name:mzn")
    private String nameMzn;
    @JsonProperty("name:nap")
    private String nameNap;
    @JsonProperty("name:os")
    private String nameOs;
    @JsonProperty("name:pa")
    private String namePa;
    @JsonProperty("name:pl")
    private String namePl;
    @JsonProperty("name:pms")
    private String namePms;
    @JsonProperty("name:pnb")
    private String namePnb;
    @JsonProperty("name:ru")
    private String nameRu;
    @JsonProperty("name:sc")
    private String nameSc;
    @JsonProperty("name:scn")
    private String nameScn;
    @JsonProperty("name:sr")
    private String nameSr;
    @JsonProperty("name:ta")
    private String nameTa;
    @JsonProperty("name:th")
    private String nameTh;
    @JsonProperty("name:tt")
    private String nameTt;
    @JsonProperty("name:uk")
    private String nameUk;
    @JsonProperty("name:ur")
    private String nameUr;
    @JsonProperty("name:vec")
    private String nameVec;
    @JsonProperty("name:xmf")
    private String nameXmf;
    @JsonProperty("name:yi")
    private String nameYi;
    @JsonProperty("name:yue")
    private String nameYue;
    @JsonProperty("name:zh")
    private String nameZh;
    @JsonProperty("population")
    private String population;
    @JsonProperty("population:date")
    private String populationDate;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("ref:FR:SIREN")
    private String refFRSIREN;
    @JsonProperty("ref:INSEE")
    private String refINSEE;
    @JsonProperty("short_name:ca")
    private String shortNameCa;
    @JsonProperty("short_name:eu")
    private String shortNameEu;
    @JsonProperty("source:name:oc")
    private String sourceNameOc;
    @JsonProperty("source:population")
    private String sourcePopulation;
    @JsonProperty("source:postal_code")
    private String sourcePostalCode;
    @JsonProperty("type")
    private String type;
    @JsonProperty("website")
    private String website;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("name:oc")
    public String getNameOc() {
        return nameOc;
    }

    @JsonProperty("name:oc")
    public void setNameOc(String nameOc) {
        this.nameOc = nameOc;
    }

    @JsonProperty("public_transport")
    public String getPublicTransport() {
        return publicTransport;
    }

    @JsonProperty("public_transport")
    public void setPublicTransport(String publicTransport) {
        this.publicTransport = publicTransport;
    }

    @JsonProperty("railway")
    public String getRailway() {
        return railway;
    }

    @JsonProperty("railway")
    public void setRailway(String railway) {
        this.railway = railway;
    }

    @JsonProperty("subway")
    public String getSubway() {
        return subway;
    }

    @JsonProperty("subway")
    public void setSubway(String subway) {
        this.subway = subway;
    }

    @JsonProperty("bus")
    public String getBus() {
        return bus;
    }

    @JsonProperty("bus")
    public void setBus(String bus) {
        this.bus = bus;
    }

    @JsonProperty("man_made")
    public String getManMade() {
        return manMade;
    }

    @JsonProperty("man_made")
    public void setManMade(String manMade) {
        this.manMade = manMade;
    }

    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    @JsonProperty("station")
    public void setStation(String station) {
        this.station = station;
    }

    @JsonProperty("wikidata")
    public String getWikidata() {
        return wikidata;
    }

    @JsonProperty("wikidata")
    public void setWikidata(String wikidata) {
        this.wikidata = wikidata;
    }

    @JsonProperty("wikipedia")
    public String getWikipedia() {
        return wikipedia;
    }

    @JsonProperty("wikipedia")
    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    @JsonProperty("wheelchair")
    public String getWheelchair() {
        return wheelchair;
    }

    @JsonProperty("wheelchair")
    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    @JsonProperty("train")
    public String getTrain() {
        return train;
    }

    @JsonProperty("train")
    public void setTrain(String train) {
        this.train = train;
    }

    @JsonProperty("search_name")
    public String getSearchName() {
        return searchName;
    }

    @JsonProperty("search_name")
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @JsonProperty("alt_name")
    public String getAltName() {
        return altName;
    }

    @JsonProperty("alt_name")
    public void setAltName(String altName) {
        this.altName = altName;
    }

    @JsonProperty("cycleway:both")
    public String getCyclewayBoth() {
        return cyclewayBoth;
    }

    @JsonProperty("cycleway:both")
    public void setCyclewayBoth(String cyclewayBoth) {
        this.cyclewayBoth = cyclewayBoth;
    }

    @JsonProperty("highway")
    public String getHighway() {
        return highway;
    }

    @JsonProperty("highway")
    public void setHighway(String highway) {
        this.highway = highway;
    }

    @JsonProperty("lanes")
    public String getLanes() {
        return lanes;
    }

    @JsonProperty("lanes")
    public void setLanes(String lanes) {
        this.lanes = lanes;
    }

    @JsonProperty("lit")
    public String getLit() {
        return lit;
    }

    @JsonProperty("lit")
    public void setLit(String lit) {
        this.lit = lit;
    }

    @JsonProperty("maxspeed")
    public String getMaxspeed() {
        return maxspeed;
    }

    @JsonProperty("maxspeed")
    public void setMaxspeed(String maxspeed) {
        this.maxspeed = maxspeed;
    }

    @JsonProperty("sidewalk")
    public String getSidewalk() {
        return sidewalk;
    }

    @JsonProperty("sidewalk")
    public void setSidewalk(String sidewalk) {
        this.sidewalk = sidewalk;
    }

    @JsonProperty("surface")
    public String getSurface() {
        return surface;
    }

    @JsonProperty("surface")
    public void setSurface(String surface) {
        this.surface = surface;
    }

    @JsonProperty("disabled")
    public String getDisabled() {
        return disabled;
    }

    @JsonProperty("disabled")
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @JsonProperty("emergency")
    public String getEmergency() {
        return emergency;
    }

    @JsonProperty("emergency")
    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    @JsonProperty("motor_vehicle")
    public String getMotorVehicle() {
        return motorVehicle;
    }

    @JsonProperty("motor_vehicle")
    public void setMotorVehicle(String motorVehicle) {
        this.motorVehicle = motorVehicle;
    }

    @JsonProperty("oneway")
    public String getOneway() {
        return oneway;
    }

    @JsonProperty("oneway")
    public void setOneway(String oneway) {
        this.oneway = oneway;
    }

    @JsonProperty("psv")
    public String getPsv() {
        return psv;
    }

    @JsonProperty("psv")
    public void setPsv(String psv) {
        this.psv = psv;
    }

    @JsonProperty("source:maxspeed")
    public String getSourceMaxspeed() {
        return sourceMaxspeed;
    }

    @JsonProperty("source:maxspeed")
    public void setSourceMaxspeed(String sourceMaxspeed) {
        this.sourceMaxspeed = sourceMaxspeed;
    }

    @JsonProperty("taxi")
    public String getTaxi() {
        return taxi;
    }

    @JsonProperty("taxi")
    public void setTaxi(String taxi) {
        this.taxi = taxi;
    }

    @JsonProperty("electrified")
    public String getElectrified() {
        return electrified;
    }

    @JsonProperty("electrified")
    public void setElectrified(String electrified) {
        this.electrified = electrified;
    }

    @JsonProperty("frequency")
    public String getFrequency() {
        return frequency;
    }

    @JsonProperty("frequency")
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("gauge")
    public String getGauge() {
        return gauge;
    }

    @JsonProperty("gauge")
    public void setGauge(String gauge) {
        this.gauge = gauge;
    }

    @JsonProperty("usage")
    public String getUsage() {
        return usage;
    }

    @JsonProperty("usage")
    public void setUsage(String usage) {
        this.usage = usage;
    }

    @JsonProperty("voltage")
    public String getVoltage() {
        return voltage;
    }

    @JsonProperty("voltage")
    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    @JsonProperty("service")
    public String getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    @JsonProperty("bridge")
    public String getBridge() {
        return bridge;
    }

    @JsonProperty("bridge")
    public void setBridge(String bridge) {
        this.bridge = bridge;
    }

    @JsonProperty("layer")
    public String getLayer() {
        return layer;
    }

    @JsonProperty("layer")
    public void setLayer(String layer) {
        this.layer = layer;
    }

    @JsonProperty("tunnel")
    public String getTunnel() {
        return tunnel;
    }

    @JsonProperty("tunnel")
    public void setTunnel(String tunnel) {
        this.tunnel = tunnel;
    }

    @JsonProperty("parking:lane:both")
    public String getParkingLaneBoth() {
        return parkingLaneBoth;
    }

    @JsonProperty("parking:lane:both")
    public void setParkingLaneBoth(String parkingLaneBoth) {
        this.parkingLaneBoth = parkingLaneBoth;
    }

    @JsonProperty("cycleway:right")
    public String getCyclewayRight() {
        return cyclewayRight;
    }

    @JsonProperty("cycleway:right")
    public void setCyclewayRight(String cyclewayRight) {
        this.cyclewayRight = cyclewayRight;
    }

    @JsonProperty("is_in:city")
    public String getIsInCity() {
        return isInCity;
    }

    @JsonProperty("is_in:city")
    public void setIsInCity(String isInCity) {
        this.isInCity = isInCity;
    }

    @JsonProperty("trolley_wire")
    public String getTrolleyWire() {
        return trolleyWire;
    }

    @JsonProperty("trolley_wire")
    public void setTrolleyWire(String trolleyWire) {
        this.trolleyWire = trolleyWire;
    }

    @JsonProperty("level")
    public String getLevel() {
        return level;
    }

    @JsonProperty("level")
    public void setLevel(String level) {
        this.level = level;
    }

    @JsonProperty("covered")
    public String getCovered() {
        return covered;
    }

    @JsonProperty("covered")
    public void setCovered(String covered) {
        this.covered = covered;
    }

    @JsonProperty("lanes:backward")
    public String getLanesBackward() {
        return lanesBackward;
    }

    @JsonProperty("lanes:backward")
    public void setLanesBackward(String lanesBackward) {
        this.lanesBackward = lanesBackward;
    }

    @JsonProperty("lanes:forward")
    public String getLanesForward() {
        return lanesForward;
    }

    @JsonProperty("lanes:forward")
    public void setLanesForward(String lanesForward) {
        this.lanesForward = lanesForward;
    }

    @JsonProperty("lanes:psv:backward")
    public String getLanesPsvBackward() {
        return lanesPsvBackward;
    }

    @JsonProperty("lanes:psv:backward")
    public void setLanesPsvBackward(String lanesPsvBackward) {
        this.lanesPsvBackward = lanesPsvBackward;
    }

    @JsonProperty("lanes:psv:forward")
    public String getLanesPsvForward() {
        return lanesPsvForward;
    }

    @JsonProperty("lanes:psv:forward")
    public void setLanesPsvForward(String lanesPsvForward) {
        this.lanesPsvForward = lanesPsvForward;
    }

    @JsonProperty("bicycle")
    public String getBicycle() {
        return bicycle;
    }

    @JsonProperty("bicycle")
    public void setBicycle(String bicycle) {
        this.bicycle = bicycle;
    }

    @JsonProperty("cycleway")
    public String getCycleway() {
        return cycleway;
    }

    @JsonProperty("cycleway")
    public void setCycleway(String cycleway) {
        this.cycleway = cycleway;
    }

    @JsonProperty("foot")
    public String getFoot() {
        return foot;
    }

    @JsonProperty("foot")
    public void setFoot(String foot) {
        this.foot = foot;
    }

    @JsonProperty("oneway:psv")
    public String getOnewayPsv() {
        return onewayPsv;
    }

    @JsonProperty("oneway:psv")
    public void setOnewayPsv(String onewayPsv) {
        this.onewayPsv = onewayPsv;
    }

    @JsonProperty("motorcycle")
    public String getMotorcycle() {
        return motorcycle;
    }

    @JsonProperty("motorcycle")
    public void setMotorcycle(String motorcycle) {
        this.motorcycle = motorcycle;
    }

    @JsonProperty("parking:lane:left")
    public String getParkingLaneLeft() {
        return parkingLaneLeft;
    }

    @JsonProperty("parking:lane:left")
    public void setParkingLaneLeft(String parkingLaneLeft) {
        this.parkingLaneLeft = parkingLaneLeft;
    }

    @JsonProperty("parking:lane:right")
    public String getParkingLaneRight() {
        return parkingLaneRight;
    }

    @JsonProperty("parking:lane:right")
    public void setParkingLaneRight(String parkingLaneRight) {
        this.parkingLaneRight = parkingLaneRight;
    }

    @JsonProperty("oneway:motor_vehicle")
    public String getOnewayMotorVehicle() {
        return onewayMotorVehicle;
    }

    @JsonProperty("oneway:motor_vehicle")
    public void setOnewayMotorVehicle(String onewayMotorVehicle) {
        this.onewayMotorVehicle = onewayMotorVehicle;
    }

    @JsonProperty("disabled:conditional")
    public String getDisabledConditional() {
        return disabledConditional;
    }

    @JsonProperty("disabled:conditional")
    public void setDisabledConditional(String disabledConditional) {
        this.disabledConditional = disabledConditional;
    }

    @JsonProperty("incline:forward")
    public String getInclineForward() {
        return inclineForward;
    }

    @JsonProperty("incline:forward")
    public void setInclineForward(String inclineForward) {
        this.inclineForward = inclineForward;
    }

    @JsonProperty("psv:conditional")
    public String getPsvConditional() {
        return psvConditional;
    }

    @JsonProperty("psv:conditional")
    public void setPsvConditional(String psvConditional) {
        this.psvConditional = psvConditional;
    }

    @JsonProperty("fixme")
    public String getFixme() {
        return fixme;
    }

    @JsonProperty("fixme")
    public void setFixme(String fixme) {
        this.fixme = fixme;
    }

    @JsonProperty("admin_level")
    public String getAdminLevel() {
        return adminLevel;
    }

    @JsonProperty("admin_level")
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    @JsonProperty("boundary")
    public String getBoundary() {
        return boundary;
    }

    @JsonProperty("boundary")
    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    @JsonProperty("name:am")
    public String getNameAm() {
        return nameAm;
    }

    @JsonProperty("name:am")
    public void setNameAm(String nameAm) {
        this.nameAm = nameAm;
    }

    @JsonProperty("name:ar")
    public String getNameAr() {
        return nameAr;
    }

    @JsonProperty("name:ar")
    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    @JsonProperty("name:az")
    public String getNameAz() {
        return nameAz;
    }

    @JsonProperty("name:az")
    public void setNameAz(String nameAz) {
        this.nameAz = nameAz;
    }

    @JsonProperty("name:be")
    public String getNameBe() {
        return nameBe;
    }

    @JsonProperty("name:be")
    public void setNameBe(String nameBe) {
        this.nameBe = nameBe;
    }

    @JsonProperty("name:bg")
    public String getNameBg() {
        return nameBg;
    }

    @JsonProperty("name:bg")
    public void setNameBg(String nameBg) {
        this.nameBg = nameBg;
    }

    @JsonProperty("name:bo")
    public String getNameBo() {
        return nameBo;
    }

    @JsonProperty("name:bo")
    public void setNameBo(String nameBo) {
        this.nameBo = nameBo;
    }

    @JsonProperty("name:br")
    public String getNameBr() {
        return nameBr;
    }

    @JsonProperty("name:br")
    public void setNameBr(String nameBr) {
        this.nameBr = nameBr;
    }

    @JsonProperty("name:ca")
    public String getNameCa() {
        return nameCa;
    }

    @JsonProperty("name:ca")
    public void setNameCa(String nameCa) {
        this.nameCa = nameCa;
    }

    @JsonProperty("name:ce")
    public String getNameCe() {
        return nameCe;
    }

    @JsonProperty("name:ce")
    public void setNameCe(String nameCe) {
        this.nameCe = nameCe;
    }

    @JsonProperty("name:co")
    public String getNameCo() {
        return nameCo;
    }

    @JsonProperty("name:co")
    public void setNameCo(String nameCo) {
        this.nameCo = nameCo;
    }

    @JsonProperty("name:csb")
    public String getNameCsb() {
        return nameCsb;
    }

    @JsonProperty("name:csb")
    public void setNameCsb(String nameCsb) {
        this.nameCsb = nameCsb;
    }

    @JsonProperty("name:de")
    public String getNameDe() {
        return nameDe;
    }

    @JsonProperty("name:de")
    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    @JsonProperty("name:el")
    public String getNameEl() {
        return nameEl;
    }

    @JsonProperty("name:el")
    public void setNameEl(String nameEl) {
        this.nameEl = nameEl;
    }

    @JsonProperty("name:eo")
    public String getNameEo() {
        return nameEo;
    }

    @JsonProperty("name:eo")
    public void setNameEo(String nameEo) {
        this.nameEo = nameEo;
    }

    @JsonProperty("name:es")
    public String getNameEs() {
        return nameEs;
    }

    @JsonProperty("name:es")
    public void setNameEs(String nameEs) {
        this.nameEs = nameEs;
    }

    @JsonProperty("name:eu")
    public String getNameEu() {
        return nameEu;
    }

    @JsonProperty("name:eu")
    public void setNameEu(String nameEu) {
        this.nameEu = nameEu;
    }

    @JsonProperty("name:fa")
    public String getNameFa() {
        return nameFa;
    }

    @JsonProperty("name:fa")
    public void setNameFa(String nameFa) {
        this.nameFa = nameFa;
    }

    @JsonProperty("name:frp")
    public String getNameFrp() {
        return nameFrp;
    }

    @JsonProperty("name:frp")
    public void setNameFrp(String nameFrp) {
        this.nameFrp = nameFrp;
    }

    @JsonProperty("name:gl")
    public String getNameGl() {
        return nameGl;
    }

    @JsonProperty("name:gl")
    public void setNameGl(String nameGl) {
        this.nameGl = nameGl;
    }

    @JsonProperty("name:he")
    public String getNameHe() {
        return nameHe;
    }

    @JsonProperty("name:he")
    public void setNameHe(String nameHe) {
        this.nameHe = nameHe;
    }

    @JsonProperty("name:hi")
    public String getNameHi() {
        return nameHi;
    }

    @JsonProperty("name:hi")
    public void setNameHi(String nameHi) {
        this.nameHi = nameHi;
    }

    @JsonProperty("name:hy")
    public String getNameHy() {
        return nameHy;
    }

    @JsonProperty("name:hy")
    public void setNameHy(String nameHy) {
        this.nameHy = nameHy;
    }

    @JsonProperty("name:it")
    public String getNameIt() {
        return nameIt;
    }

    @JsonProperty("name:it")
    public void setNameIt(String nameIt) {
        this.nameIt = nameIt;
    }

    @JsonProperty("name:ja")
    public String getNameJa() {
        return nameJa;
    }

    @JsonProperty("name:ja")
    public void setNameJa(String nameJa) {
        this.nameJa = nameJa;
    }

    @JsonProperty("name:ka")
    public String getNameKa() {
        return nameKa;
    }

    @JsonProperty("name:ka")
    public void setNameKa(String nameKa) {
        this.nameKa = nameKa;
    }

    @JsonProperty("name:kk")
    public String getNameKk() {
        return nameKk;
    }

    @JsonProperty("name:kk")
    public void setNameKk(String nameKk) {
        this.nameKk = nameKk;
    }

    @JsonProperty("name:kn")
    public String getNameKn() {
        return nameKn;
    }

    @JsonProperty("name:kn")
    public void setNameKn(String nameKn) {
        this.nameKn = nameKn;
    }

    @JsonProperty("name:ko")
    public String getNameKo() {
        return nameKo;
    }

    @JsonProperty("name:ko")
    public void setNameKo(String nameKo) {
        this.nameKo = nameKo;
    }

    @JsonProperty("name:la")
    public String getNameLa() {
        return nameLa;
    }

    @JsonProperty("name:la")
    public void setNameLa(String nameLa) {
        this.nameLa = nameLa;
    }

    @JsonProperty("name:lt")
    public String getNameLt() {
        return nameLt;
    }

    @JsonProperty("name:lt")
    public void setNameLt(String nameLt) {
        this.nameLt = nameLt;
    }

    @JsonProperty("name:lv")
    public String getNameLv() {
        return nameLv;
    }

    @JsonProperty("name:lv")
    public void setNameLv(String nameLv) {
        this.nameLv = nameLv;
    }

    @JsonProperty("name:mhr")
    public String getNameMhr() {
        return nameMhr;
    }

    @JsonProperty("name:mhr")
    public void setNameMhr(String nameMhr) {
        this.nameMhr = nameMhr;
    }

    @JsonProperty("name:ml")
    public String getNameMl() {
        return nameMl;
    }

    @JsonProperty("name:ml")
    public void setNameMl(String nameMl) {
        this.nameMl = nameMl;
    }

    @JsonProperty("name:mr")
    public String getNameMr() {
        return nameMr;
    }

    @JsonProperty("name:mr")
    public void setNameMr(String nameMr) {
        this.nameMr = nameMr;
    }

    @JsonProperty("name:mzn")
    public String getNameMzn() {
        return nameMzn;
    }

    @JsonProperty("name:mzn")
    public void setNameMzn(String nameMzn) {
        this.nameMzn = nameMzn;
    }

    @JsonProperty("name:nap")
    public String getNameNap() {
        return nameNap;
    }

    @JsonProperty("name:nap")
    public void setNameNap(String nameNap) {
        this.nameNap = nameNap;
    }

    @JsonProperty("name:os")
    public String getNameOs() {
        return nameOs;
    }

    @JsonProperty("name:os")
    public void setNameOs(String nameOs) {
        this.nameOs = nameOs;
    }

    @JsonProperty("name:pa")
    public String getNamePa() {
        return namePa;
    }

    @JsonProperty("name:pa")
    public void setNamePa(String namePa) {
        this.namePa = namePa;
    }

    @JsonProperty("name:pl")
    public String getNamePl() {
        return namePl;
    }

    @JsonProperty("name:pl")
    public void setNamePl(String namePl) {
        this.namePl = namePl;
    }

    @JsonProperty("name:pms")
    public String getNamePms() {
        return namePms;
    }

    @JsonProperty("name:pms")
    public void setNamePms(String namePms) {
        this.namePms = namePms;
    }

    @JsonProperty("name:pnb")
    public String getNamePnb() {
        return namePnb;
    }

    @JsonProperty("name:pnb")
    public void setNamePnb(String namePnb) {
        this.namePnb = namePnb;
    }

    @JsonProperty("name:ru")
    public String getNameRu() {
        return nameRu;
    }

    @JsonProperty("name:ru")
    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @JsonProperty("name:sc")
    public String getNameSc() {
        return nameSc;
    }

    @JsonProperty("name:sc")
    public void setNameSc(String nameSc) {
        this.nameSc = nameSc;
    }

    @JsonProperty("name:scn")
    public String getNameScn() {
        return nameScn;
    }

    @JsonProperty("name:scn")
    public void setNameScn(String nameScn) {
        this.nameScn = nameScn;
    }

    @JsonProperty("name:sr")
    public String getNameSr() {
        return nameSr;
    }

    @JsonProperty("name:sr")
    public void setNameSr(String nameSr) {
        this.nameSr = nameSr;
    }

    @JsonProperty("name:ta")
    public String getNameTa() {
        return nameTa;
    }

    @JsonProperty("name:ta")
    public void setNameTa(String nameTa) {
        this.nameTa = nameTa;
    }

    @JsonProperty("name:th")
    public String getNameTh() {
        return nameTh;
    }

    @JsonProperty("name:th")
    public void setNameTh(String nameTh) {
        this.nameTh = nameTh;
    }

    @JsonProperty("name:tt")
    public String getNameTt() {
        return nameTt;
    }

    @JsonProperty("name:tt")
    public void setNameTt(String nameTt) {
        this.nameTt = nameTt;
    }

    @JsonProperty("name:uk")
    public String getNameUk() {
        return nameUk;
    }

    @JsonProperty("name:uk")
    public void setNameUk(String nameUk) {
        this.nameUk = nameUk;
    }

    @JsonProperty("name:ur")
    public String getNameUr() {
        return nameUr;
    }

    @JsonProperty("name:ur")
    public void setNameUr(String nameUr) {
        this.nameUr = nameUr;
    }

    @JsonProperty("name:vec")
    public String getNameVec() {
        return nameVec;
    }

    @JsonProperty("name:vec")
    public void setNameVec(String nameVec) {
        this.nameVec = nameVec;
    }

    @JsonProperty("name:xmf")
    public String getNameXmf() {
        return nameXmf;
    }

    @JsonProperty("name:xmf")
    public void setNameXmf(String nameXmf) {
        this.nameXmf = nameXmf;
    }

    @JsonProperty("name:yi")
    public String getNameYi() {
        return nameYi;
    }

    @JsonProperty("name:yi")
    public void setNameYi(String nameYi) {
        this.nameYi = nameYi;
    }

    @JsonProperty("name:yue")
    public String getNameYue() {
        return nameYue;
    }

    @JsonProperty("name:yue")
    public void setNameYue(String nameYue) {
        this.nameYue = nameYue;
    }

    @JsonProperty("name:zh")
    public String getNameZh() {
        return nameZh;
    }

    @JsonProperty("name:zh")
    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    @JsonProperty("population")
    public String getPopulation() {
        return population;
    }

    @JsonProperty("population")
    public void setPopulation(String population) {
        this.population = population;
    }

    @JsonProperty("population:date")
    public String getPopulationDate() {
        return populationDate;
    }

    @JsonProperty("population:date")
    public void setPopulationDate(String populationDate) {
        this.populationDate = populationDate;
    }

    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postal_code")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("ref:FR:SIREN")
    public String getRefFRSIREN() {
        return refFRSIREN;
    }

    @JsonProperty("ref:FR:SIREN")
    public void setRefFRSIREN(String refFRSIREN) {
        this.refFRSIREN = refFRSIREN;
    }

    @JsonProperty("ref:INSEE")
    public String getRefINSEE() {
        return refINSEE;
    }

    @JsonProperty("ref:INSEE")
    public void setRefINSEE(String refINSEE) {
        this.refINSEE = refINSEE;
    }

    @JsonProperty("short_name:ca")
    public String getShortNameCa() {
        return shortNameCa;
    }

    @JsonProperty("short_name:ca")
    public void setShortNameCa(String shortNameCa) {
        this.shortNameCa = shortNameCa;
    }

    @JsonProperty("short_name:eu")
    public String getShortNameEu() {
        return shortNameEu;
    }

    @JsonProperty("short_name:eu")
    public void setShortNameEu(String shortNameEu) {
        this.shortNameEu = shortNameEu;
    }

    @JsonProperty("source:name:oc")
    public String getSourceNameOc() {
        return sourceNameOc;
    }

    @JsonProperty("source:name:oc")
    public void setSourceNameOc(String sourceNameOc) {
        this.sourceNameOc = sourceNameOc;
    }

    @JsonProperty("source:population")
    public String getSourcePopulation() {
        return sourcePopulation;
    }

    @JsonProperty("source:population")
    public void setSourcePopulation(String sourcePopulation) {
        this.sourcePopulation = sourcePopulation;
    }

    @JsonProperty("source:postal_code")
    public String getSourcePostalCode() {
        return sourcePostalCode;
    }

    @JsonProperty("source:postal_code")
    public void setSourcePostalCode(String sourcePostalCode) {
        this.sourcePostalCode = sourcePostalCode;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
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
