package eu.akka.mobidata.mashup.services.interfaces;

import org.springframework.cache.annotation.Cacheable;

/**
 * Service communicating with the Navitia API
 *
 * @author Mohamed.KARAMI
 */
public interface INavitiaService {

    /**
     * Finds and returns lines.
     *
     * @return the lines if found, or null if not found
     */
    @Cacheable("lines")
    String findLines();

    /**
     * Finds and returns journeys.
     *
     * @param targetToken target Token
     * @param fromCoordinates from Coordinates
     * @param toCoordinates to Coordinates
     * @return the journeys if found, or null if not found
     */
    @Cacheable("journeys-json")
    String findJsonJourneys(String targetToken, String fromCoordinates, String toCoordinates);

}
