package eu.akka.mobidata.mashup.util;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * Handles geometry factory
 *
 * @author Mohamed.KARAMI
 */
public class GeometryTools {

    public static final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
}
