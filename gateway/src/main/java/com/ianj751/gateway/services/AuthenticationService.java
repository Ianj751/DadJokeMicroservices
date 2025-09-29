package com.ianj751.gateway.services;

import com.ianj751.gateway.models.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ianj751.gateway.configuration.JwtService;
import com.ianj751.gateway.models.AuthRequest;
import com.ianj751.gateway.models.AuthResponse;
import com.ianj751.gateway.models.Role;
import com.ianj751.gateway.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        var user = User.builder().username(request.username()).password(passwordEncoder.encode(request.password()))
                .role(Role.USER).build();

        // returning a response indicating that a user already exists is an information
        // disclosure vulnerability
        if (!(userRepository.findByUsername(user.getUsername()).isPresent())) {
            userRepository.save(user);
        }

        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(AuthRequest request) throws UsernameNotFoundException {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = userRepository.findByUsername(request.username()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);

    }

}
