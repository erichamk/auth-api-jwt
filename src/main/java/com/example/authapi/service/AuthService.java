package com.example.authapi.service;

import com.example.authapi.dto.*;
import com.example.authapi.entity.User;
import com.example.authapi.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repo,
                       PasswordEncoder encoder,
                       JwtService jwtService) {

        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    // Save new User in DB and return new JWT
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));

        repo.save(user);

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    // validate username and password, return new JWT
    public AuthResponse login(LoginRequest request) {

        User user = repo.findByUsername(request.getUsername())
                .orElseThrow();

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}