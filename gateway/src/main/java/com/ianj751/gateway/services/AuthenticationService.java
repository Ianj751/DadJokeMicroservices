package com.ianj751.gateway.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ianj751.gateway.configuration.JwtService;
import com.ianj751.gateway.models.AuthRequest;
import com.ianj751.gateway.models.AuthResponse;
import com.ianj751.gateway.models.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(AuthRequest request) {
        var user = User.builder().username(request.username()).password(passwordEncoder.encode(request.password()))
                .roles(Role.USER.name()).build();

        // TODO: save user in db if not exists

        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(AuthRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

}
