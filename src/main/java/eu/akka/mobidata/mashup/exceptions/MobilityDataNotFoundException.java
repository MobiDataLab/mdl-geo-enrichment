package eu.akka.mobidata.mashup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found!")
public class MobilityDataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public MobilityDataNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
