package org.practice.libraryspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.libraryspring.constant.Role;
import org.practice.libraryspring.dto.request.LoginRequest;
import org.practice.libraryspring.dto.request.RegisterRequest;
import org.practice.libraryspring.dto.response.LoginResponse;
import org.practice.libraryspring.dto.response.RegisterResponse;
import org.practice.libraryspring.entity.User;
import org.practice.libraryspring.repository.UserRepository;
import org.practice.libraryspring.service.AuthService;
import org.practice.libraryspring.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.LIBRARIAN)
                .build();
        userRepository.save(user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public RegisterResponse registerAdmin(RegisterRequest registerRequest) {
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authentication.getPrincipal();

        if(!authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().toString())
                .token(token)
                .build();
    }
}
