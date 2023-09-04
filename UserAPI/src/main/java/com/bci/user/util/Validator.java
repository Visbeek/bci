package com.bci.user.util;

import com.bci.user.exception.FormatValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    @Value("${format.password.regexp}")
    private String passwordFormat;

    @Value("${format.password.message}")
    private String passwordFormatMessage;

    public void validateMailFormat(String email) {
        if (!email.matches("^[a-z]{1,7}@[a-zA-Z0-9-]+(\\.)+cl$"))
            throw new FormatValidationException("Campo email invalido. Debe respetar el formato aaaaaaa@dominio.cl");
    }

    public void validatePasswordFormat(String password) {
        if (!password.matches(passwordFormat)) throw new FormatValidationException(passwordFormatMessage);
    }

}
