package cz.uhk.ppro.pushntf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectExistsException extends Exception {

    public ObjectExistsException(String message) {
        super(message);
    }

}
