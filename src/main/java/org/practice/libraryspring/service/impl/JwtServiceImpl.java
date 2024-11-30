package org.practice.libraryspring.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.practice.libraryspring.entity.User;
import org.practice.libraryspring.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class JwtServiceImpl implements JwtService {
    private final String secret;
    private final long expiration;
    private final String issuer;
    private final Algorithm algorithm;

    public JwtServiceImpl() {
        this.secret = "odaooekmdakmedapok213jeqduhokawmeu3uudnsamdiamvbhefadjmc";
        this.expiration = 1800;
        this.issuer = "libraryspring";
        this.algorithm = Algorithm.HMAC512(this.secret);
    }

    @Override
    public String generateToken(User user) {
        try{
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(expiration))
                    .withClaim("roles", String.valueOf(user.getAuthorities().stream().map(GrantedAuthority::getAuthority)))
                    .withIssuer(issuer)
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User getUserFromToken(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}