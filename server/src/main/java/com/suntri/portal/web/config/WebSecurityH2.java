package com.suntri.portal.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(10)
@Configuration
public class WebSecurityH2 extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/h2-console/**")
                .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
            .and()
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")
            .and()
                .headers()
                    .frameOptions()
                        .sameOrigin();
    }

}
