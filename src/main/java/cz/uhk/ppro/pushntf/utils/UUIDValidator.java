package cz.uhk.ppro.pushntf.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UUIDValidator {

    public boolean isUuidStringValid(String uuid){
        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        return  UUID_REGEX.matcher(uuid).matches();

    }

}
