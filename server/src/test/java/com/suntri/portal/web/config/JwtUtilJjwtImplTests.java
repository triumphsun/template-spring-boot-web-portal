package com.suntri.portal.web.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class JwtUtilJjwtImplTests {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void givenAnInvalidToken_whenValidating_then(){
        String token = "invalid-token";
        Optional<Authentication> optAuthentication = this.jwtUtil.validate(token);
        Assertions.assertTrue(optAuthentication.isEmpty());
    }

}
