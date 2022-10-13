package com.api.coursesmanager.services;

import com.api.coursesmanager.dtos.AuthDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    public Object authenticate(AuthDto authDto){
        String url = "http://localhost:8080/realms/courses-manager/protocol/openid-connect/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap();
        requestBody.add("client_id", "web-app");
        requestBody.add("client_secret", "IWBER6HtNogkat4GccxBWIcYuD8q8hKe");
        requestBody.add("grant_type", "password");
        requestBody.add("username", authDto.getUsername());
        requestBody.add("password", authDto.getPassword());

        HttpEntity formEntity = new HttpEntity(requestBody, headers);

        ResponseEntity<Object> response =
                restTemplate.exchange(url, HttpMethod.POST, formEntity, Object.class);
        return response.getBody();
    }
}
