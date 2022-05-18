package eu.akka.mobidata.mashup.util;

/**
 * Constants.
 *
 * @author Mohamed.KARAMI
 */
public class Constants {
    public static String NPM_GET_WIN_PATH = "npm.cmd config get prefix";
    public static String OSM_TO_GEOJSON = "osmtogeojson" + (Utils.isWindows() ? ".cmd" : "");
}
