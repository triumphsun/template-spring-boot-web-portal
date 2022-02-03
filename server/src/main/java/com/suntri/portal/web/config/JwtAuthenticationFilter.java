package com.suntri.portal.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.setAuthenticationManager(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/rest/token", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String payloadStr = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining());
            Map<String, String> payload = objectMapper.readValue(payloadStr, Map.class);
            String username = payload.get("username");
            String password = payload.get("password");
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseEntity = new HashMap<>();
        responseEntity.put("token", this.jwtUtil.sign(authResult));
        responseEntity.put("timestamp", System.currentTimeMillis());
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(responseEntity));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseEntity = new HashMap<>();
        responseEntity.put("timestamp", System.currentTimeMillis());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(responseEntity));
    }
}
