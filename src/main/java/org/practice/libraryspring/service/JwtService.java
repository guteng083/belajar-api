package org.practice.libraryspring.service;

import org.practice.libraryspring.entity.User;

public interface JwtService {
    String generateToken(User user);
    User getUserFromToken(String token);
    boolean validateToken(String token);
}
