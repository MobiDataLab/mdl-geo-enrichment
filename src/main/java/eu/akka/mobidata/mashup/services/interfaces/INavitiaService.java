package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service communicating with the Navitia API
 *
 * @author Mohamed.KARAMI
 */
public interface INavitiaService {

    /**
     * Finds and returns lines.
     *
     * @param targetToken     target Token
     * @param fromCoordinates from Coordinates
     * @param toCoordinates   to Coordinates
     * @return the lines if found, or null if not found
     */
    String findLines(String targetToken, String fromCoordinates, String toCoordinates);

    /**
     * Finds and returns journeys.
     *
     * @param targetToken     target Token
     * @param fromCoordinates from Coordinates
     * @param toCoordinates   to Coordinates
     * @return the journeys if found, or null if not found
     */
    String findJsonJourneys(String targetToken, String fromCoordinates, String toCoordinates);
}
