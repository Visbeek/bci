package com.bci.user.util;

import com.bci.user.exception.FormatValidationException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ValidatorTest {

    private static final String EMAIL = "hola@test.cl";
    private static final String ALT_EMAIL = "hola@test.com";
    private static final String PASSWORD = "Password12";
    private static final String ALT_PASSWORD = "password1";

    @Autowired
    Validator validator;

    @Test
    void validateMailFormatTest(){
        assertDoesNotThrow(() -> validator.validateMailFormat(EMAIL));
    }

    @Test
    void validateMailFormatInvalidTest(){
        assertThrows(FormatValidationException.class, () -> validator.validateMailFormat(ALT_EMAIL));
    }

    @Test
    void validatePasswordFormatTest(){
        assertDoesNotThrow(() -> validator.validatePasswordFormat(PASSWORD));
    }

    @Test
    void validatePasswordFormatInvalidTest(){
        assertThrows(FormatValidationException.class, () -> validator.validatePasswordFormat(ALT_PASSWORD));
    }

}
