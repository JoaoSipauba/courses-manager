package com.api.coursesmanager.services;

import com.api.coursesmanager.dtos.AuthDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    @Value("${keycloak.client-id}")
    private String clientId;
    @Value("${keycloak.client-secret}")
    private String clientSecret;
    @Value("${keycloak.auth-url}")
    private String authUrl;

    public Object authenticate(AuthDto authDto){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap();
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("grant_type", "password");
        requestBody.add("username", authDto.getUsername());
        requestBody.add("password", authDto.getPassword());

        HttpEntity formEntity = new HttpEntity(requestBody, headers);

        ResponseEntity<Object> response =
                restTemplate.exchange(authUrl, HttpMethod.POST, formEntity, Object.class);
        return response.getBody();
    }
}
