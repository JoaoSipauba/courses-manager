package com.api.coursesmanager.Utils;

import lombok.Getter;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class JwtUtils {
    private static HttpServletRequest request = getCurrentHttpRequest();
    private static KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
    private static KeycloakPrincipal principal = (KeycloakPrincipal)token.getPrincipal();
    private static KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
    private static AccessToken accessToken = session.getToken();

    private static HttpServletRequest getCurrentHttpRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        request = ((ServletRequestAttributes)requestAttributes).getRequest();
        return request;
    }

    @Getter
    private static String username = accessToken.getPreferredUsername();

    @Getter
    private static String email = accessToken.getEmail();

    @Getter
    private static String lastname = accessToken.getFamilyName();

    @Getter
    private static String firstname = accessToken.getGivenName();

    @Getter
    private static String fullName = accessToken.getName();
}
