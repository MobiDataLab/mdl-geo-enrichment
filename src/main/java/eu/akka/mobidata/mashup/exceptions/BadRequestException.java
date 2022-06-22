package eu.akka.mobidata.mashup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles bad request exception.
 *
 * @author Mohamed.KARAMI
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request!")
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
