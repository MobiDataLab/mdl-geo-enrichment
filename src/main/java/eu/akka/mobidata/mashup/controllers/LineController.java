package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.domain.navitia.NavitiaContainer;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.NavitiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles the mobility data / lines in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping("/api/v1/navitia")
public class LineController {

    @Autowired
    private NavitiaService navitiaService;

    /**
     * Returns the aggregated information for lines.
     *
     * @return the JSON representation of the lines
     */
    @RequestMapping(value = "getLines", method = RequestMethod.GET)
    public @ResponseBody
    NavitiaContainer getLines() {
        // Get lines from Navitia
        NavitiaContainer lines = navitiaService.findLines();
        if (lines == null) {
            throw new MobilityDataNotFoundException();
        }
        return lines;
    }

}
