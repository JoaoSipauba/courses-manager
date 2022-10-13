package com.api.coursesmanager.controllers;

import com.api.coursesmanager.dtos.AuthDto;
import com.api.coursesmanager.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Object> authentication(@RequestBody @Valid AuthDto authDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(authDto));
    }
}
