package org.practice.libraryspring.service;

import org.practice.libraryspring.dto.request.LoginRequest;
import org.practice.libraryspring.dto.request.RegisterRequest;
import org.practice.libraryspring.dto.response.LoginResponse;
import org.practice.libraryspring.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    RegisterResponse registerAdmin(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
