package com.example.ecommercespring.auth;

import com.example.ecommercespring.config.JwtService;
import com.example.ecommercespring.model.Client;
import com.example.ecommercespring.repo.ClientRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @Autowired
    private ClientRepository clientRepo;

    private JwtService jwtService = new JwtService();

    private static Map<Integer, String> tokens = new HashMap<>();

    @GetMapping("/authenticated")
    public String getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(service.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest registerRequest) {
        String token = service.authenticate(registerRequest).getToken();

        int hash = 0;
        try {
            // Create a SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convert the input string to bytes using UTF-8 encoding
            byte[] inputBytes = token.getBytes(StandardCharsets.UTF_8);

            // Update the MessageDigest with the input bytes
            digest.update(inputBytes);

            // Get the hash value as an array of bytes
            byte[] hashBytes = digest.digest();

            // Convert the hash bytes to an integer
            hash = bytesToInt(hashBytes);

            // Print the hash code
            System.out.println("Hash Code: " + hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        tokens.put(hash, token);
        System.out.println(token);
        return ResponseEntity.ok(service.authenticate(registerRequest));
    }

    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientRepo.findAll();
    }

    @GetMapping("/extractClaims/{hash}")
    public String getClaims(@PathVariable("hash") int hash){
        String token = tokens.get(hash);
        System.out.println(token);
        return jwtService.extractAllClaims(token).toString();
    }


    private static int bytesToInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (bytes[i] & 0xff) << (8 * i);
        }
        return result;
    }
}

