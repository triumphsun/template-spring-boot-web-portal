package com.suntri.portal.web.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtUtilJjwtImpl implements JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String sign(Authentication authentication) {
        Long now = System.currentTimeMillis();
        String username = null;
        if(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User){
            User user = (User) authentication.getPrincipal();
            username = user.getUsername();
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleClaimStr = authorities.stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", "").toLowerCase())
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roleClaimStr)
                .signWith(SignatureAlgorithm.HS256, this.secret.getBytes())
                .compact();
    }

    @Override
    public Optional<Authentication> validate(String token) {
        try {
            Jwt<Header, Claims> jwt = Jwts.parser().setSigningKey(this.secret.getBytes()).parse(token);
            Claims claims = jwt.getBody();
            String username = claims.getSubject();
            String roleClaimStr = claims.get("roles", String.class);
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(roleClaimStr.split(","))
                    .map(roleName -> new SimpleGrantedAuthority(String.format("ROLE_%s", roleName.trim().toUpperCase())))
                    .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setAuthenticated(true);
            return Optional.of(authentication);
        } catch (ExpiredJwtException e) {
            return Optional.empty();
        } catch (MalformedJwtException e) {
            return Optional.empty();
        } catch (SignatureException e) {
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
