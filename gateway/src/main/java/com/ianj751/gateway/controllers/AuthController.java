package com.ianj751.gateway.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ianj751.gateway.models.AuthRequest;
import com.ianj751.gateway.models.AuthResponse;

import com.ianj751.gateway.services.AuthenticationService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            // Error response format
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("user could not be found"));
        }
    }

    @AllArgsConstructor
    @Data
    public static class ErrorResponse {
        private String error;

    }

}
