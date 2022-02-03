package com.suntri.portal.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Order(90)
@Configuration
public class WebSecurityRest extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/rest/**")
                .csrf().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), this.jwtUtil))
                .addFilterAfter(jwtAuthorizationFilter, JwtAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/rest/token").permitAll()
                    .anyRequest().authenticated()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }
}
