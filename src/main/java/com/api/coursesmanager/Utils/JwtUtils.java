package com.api.coursesmanager.Utils;

import lombok.Getter;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUtils {
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    private KeycloakPrincipal principal = (KeycloakPrincipal) auth.getPrincipal();

    private KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
    private AccessToken accessToken = session.getToken();

    @Getter
    private String username = accessToken.getPreferredUsername();

    @Getter
    private String email = accessToken.getEmail();

    @Getter
    private String lastname = accessToken.getFamilyName();

    @Getter
    private String firstname = accessToken.getGivenName();

    @Getter
    private String fullName = accessToken.getName();
}
