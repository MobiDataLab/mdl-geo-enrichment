package eu.akka.mobidata.mashup.util;

/**
 * Constants.
 *
 * @author Mohamed.KARAMI
 */
public class Constants {

    public static final String[] AUTH_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**"
    };
    public static final String GTFS_CONFIG_JSON = """
                        {
                            "agencies": [
                                {
                                    "agency_key": "my_agency_key",
                                    "path":"gtfs_path",
                                    "outputFormat": "stops",
                                    "exclude": [
                                        "calendar",
                                        "calendar_dates",
                                        "frequencies",
                                        "routes",
                                        "shapes",
                                        "stop_times",
                                        "trips"
                                    ]
                                }
                            ]
                        }
            """;
    public static String NPM_GET_WIN_PATH = "npm.cmd config get prefix";
    public static String OSM_TO_GEOJSON = "osmtogeojson" + (Utils.isWindows() ? ".cmd" : "");
    public static String GTFS_TO_GEOJSON = "gtfs-to-geojson" + (Utils.isWindows() ? ".cmd" : "");
}
