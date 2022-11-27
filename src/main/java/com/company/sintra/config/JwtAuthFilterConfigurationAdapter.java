package com.company.sintra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JwtAuthFilterConfigurationAdapter extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthRequestFilter authRequestFilter;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        log.info("Added auth request filter");
        builder.addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
