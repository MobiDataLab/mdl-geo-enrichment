package eu.akka.mobidata.mashup.util;

public class Utils {

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
