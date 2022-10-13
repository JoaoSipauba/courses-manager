package com.api.coursesmanager.config.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter = new KeycloakJwtAuthenticationConverter();

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
    };
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authz -> authz.antMatchers(AUTH_WHITELIST).permitAll()
                                .antMatchers("/**").authenticated())
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);
    }

}
