package com.example.ecommercespring.auth;

import com.example.ecommercespring.config.JwtService;
import com.example.ecommercespring.model.Client;
import com.example.ecommercespring.repo.ClientRepository;
import com.example.ecommercespring.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientRepository clientRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        var user = clientRepo.findByUsername(authenticationRequest.getUsername()).orElseThrow();
        HashMap<String, Object> claims = new HashMap<>();
        var jwt = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Client.builder().prenom(request.getPrenom())
                        .nom(request.getNom())
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .age(request.getAge())
                        .sexe(request.getSexe())
                        .etatCivil(request.getEtatCivil())
                        .build();
        clientRepo.saveAndFlush(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }
}
