package com.suntri.portal.web.config;

import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface JwtUtil {
    public String sign(Authentication authentication);
    public Optional<Authentication> validate(String token);
}
