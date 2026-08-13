package com.example.financeAPI.service;

import com.example.financeAPI.dtos.authDtos.AuthResponseDto;
import com.example.financeAPI.dtos.authDtos.LoginRequestDto;
import com.example.financeAPI.dtos.authDtos.RegisterRequestDto;
import com.example.financeAPI.models.User;
import com.example.financeAPI.repositories.UserRepository;
import com.example.financeAPI.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDto register(RegisterRequestDto dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already registered");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                encodedPassword);
        repository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password."));
        if (!passwordEncoder.matches(dto.getPassword(), dto.getEmail())){
            throw new IllegalArgumentException("Invalid Email or Password.");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }


}
