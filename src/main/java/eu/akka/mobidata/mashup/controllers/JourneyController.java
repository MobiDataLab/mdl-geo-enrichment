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
 * Handles the mobility data / journeys in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping("/api/v1/navitia")
public class JourneyController {

    @Autowired
    private NavitiaService navitiaService;

    @RequestMapping(value = "getJourneys", method = RequestMethod.GET)
    public @ResponseBody
    NavitiaContainer getJourneys() {
        // Get journeys from Navitia
        NavitiaContainer lines = navitiaService.findJourneys();
        if (lines == null) {
            throw new MobilityDataNotFoundException();
        }

        return lines;
    }

}
