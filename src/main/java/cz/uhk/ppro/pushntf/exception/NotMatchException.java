package cz.uhk.ppro.pushntf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class NotMatchException extends Exception {

    public NotMatchException(String message) {
        super(message);
    }

    @ResponseBody
    public String responseBody() {
        return "Thanks For Posting!!!";
    }

}
