package com.bci.user.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JWTUtilsTest {

    private static final String EMAIL = "test@mail.cl";
    @Test
    void generateJWTTest(){
        String response = JWTUtils.generateJWT(EMAIL);
        assertThat(response).isNotNull();
    }
}
