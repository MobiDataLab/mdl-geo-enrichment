package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service handling generic Json APIs
 *
 * @author Mohamed.KARAMI
 */
public interface IGenericJsonService {

    /**
     * get Stops on Json format
     * @param url url of the api
     * @param token token of the api
     * @return the stop points on Json) format
     */
    String getJsonStops(String url, String token);
}
