package org.practice.libraryspring.service;

import org.practice.libraryspring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(User user);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
