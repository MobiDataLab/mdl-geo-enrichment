package eu.akka.mobidata.mashup.util;
/**
 * Constants.
 *
 * @author Mohamed.KARAMI
 */
public class Constants {
    public static String NPM_GET_PATH = "npm" + (Utils.isWindows() ? ".cmd" : "") + " config get prefix";
    public static String OSM_TO_GEOJSON = "osmtogeojson" + (Utils.isWindows() ? ".cmd" : "");
}
