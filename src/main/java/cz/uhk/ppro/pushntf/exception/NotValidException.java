package cz.uhk.ppro.pushntf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotValidException extends Exception {

    public NotValidException(String message) {
        super(message);
    }

}
