package com.company.sintra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final RequestMatcher BASIC_REQUESTS = new AntPathRequestMatcher("/api/basic/**");

    private static final RequestMatcher BEARER_REQUESTS = new NegatedRequestMatcher(BASIC_REQUESTS);

    private final JwtAuthFilterConfigurationAdapter jwtAuthFilterConfigurationAdapter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/v2/user").hasAuthority("USER")
                .antMatchers("/v2/manager").hasAuthority("MANAGER")
                .antMatchers("/v2/hello").permitAll()
                .antMatchers("/api/v1/**").permitAll();
        http.logout().disable();
        http.formLogin().disable();
        http.apply(jwtAuthFilterConfigurationAdapter);
        super.configure(http);
    }
}




