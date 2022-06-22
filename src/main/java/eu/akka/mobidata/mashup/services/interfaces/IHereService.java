package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service communicating with Here API
 *
 * @author Mohamed.KARAMI
 */
public interface IHereService {

    /**
     * Finds and returns transit routes.
     *
     * @param apiKey          here account api key
     * @param fromCoordinates from Coordinates
     * @param toCoordinates   to Coordinates
     * @return here journeys if found, or null if not found
     */
    String findHereRoutes(String apiKey, String fromCoordinates, String toCoordinates);

    /**
     * Finds and returns near stations.
     *
     * @param apiKey      here account api key
     * @param coordinates coordinates
     * @return here journeys if found, or null if not found
     */
    String findNearStations(String apiKey, String coordinates);
}
