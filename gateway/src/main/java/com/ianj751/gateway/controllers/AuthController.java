package com.ianj751.gateway.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class AuthController {
    record AuthResponse(String jwt) {
    }

    record AuthRequest(String username, String password) {
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest entity) {
        // TODO: hash details, store in h2, create jwt from details, return to user

        AuthResponse resp = new AuthResponse(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest entity) {
        // TODO: hash details, verify entity exists in h2, return jwt from details,
        // return to user

        AuthResponse resp = new AuthResponse(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30");
        return ResponseEntity.ok(resp);
    }

}
