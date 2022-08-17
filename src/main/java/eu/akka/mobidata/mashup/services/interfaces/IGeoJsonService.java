package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service communicating with the GeoJson API
 *
 * @author Mohamed.KARAMI
 */
public interface IGeoJsonService {

    /**
     * get GeoJson Bus Stops
     *
     * @param url
     * @param token
     * @return
     */
    String getGeoJsonBusStops(String url, String token);
}
