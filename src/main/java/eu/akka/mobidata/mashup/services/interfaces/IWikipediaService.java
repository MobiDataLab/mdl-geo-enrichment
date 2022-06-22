package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service communicating with the Wikipedia API.
 *
 * @author christer
 */
public interface IWikipediaService {
    /**
     * Returns an extract (short version) of the description of a page in
     * Wikipedia
     *
     * @param title the title of the Wikipedia page to get the extract from
     * @return the extract of the page or null if not found
     */
    String getExtract(String title);

}
