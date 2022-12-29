package cz.uhk.ppro.pushntf.utils;

import cz.uhk.ppro.pushntf.model.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UUIDValidatorTest {

    @Autowired
    private UUIDValidator validator;

    @Test
    void isUuidStringInvalid() {
        //give
        String uuid = "556546544e9bdebd";
        //when
        boolean expected = validator.isUuidStringValid(uuid);
        //then
        assertThat(expected).isFalse();
    }
}