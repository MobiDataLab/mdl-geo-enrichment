package eu.akka.mobidata.mashup.util;

import eu.akka.mobidata.mashup.services.OsmService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Handles the Openstreetmap format.
 *
 * @author Mohamed.KARAMI
 */
public class OsmTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(OsmTools.class);

    public static String convertOsmToGeoJson(String navObject) throws IOException {
        try {
            //convert osm to geojson
            Runtime runtime = Runtime.getRuntime();

            // write osm response to temporary file
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, navObject.getBytes(StandardCharsets.UTF_8));

            // get npm path
            Process process = runtime.exec(Constants.NPM_GET_PATH);
            String npmPath = IOUtils.toString(process.getInputStream(), Charset.defaultCharset()).replace("\n", "");

            // convert osm to geojson
            String[] command = { npmPath + File.separator + Constants.OSM_TO_GEOJSON, tempFile.toString() };
            process = runtime.exec(command);
            String stdout = IOUtils.toString(process.getInputStream(), Charset.defaultCharset());

            // remove temporary file
            Files.delete(tempFile);
            return stdout;
        } catch (IOException e) {
            LOGGER.error("Please make sure nodejs and osmtogeojson module is installed!");
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
