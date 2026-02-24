package com.projet.annuaire.controller;

import com.projet.annuaire.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class
AuthController {

    @Value("${jwt.secret}")
    protected String jwtSecret;

    protected final AuthenticationProvider authenticationProvider;



    @PostMapping("/connexion")
    public ResponseEntity<String> login(@RequestBody Utilisateur utilisateur) {

        try {

            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(utilisateur.getEmail(), utilisateur.getPassword()));

        } catch (Exception e) {
            return new ResponseEntity<>("Email ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        }

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        
        String jwt = Jwts
                .builder()
                .subject(utilisateur.getEmail())
                .signWith(key)
                .compact();

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
