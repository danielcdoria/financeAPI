package com.example.financeAPI.controller;

import com.example.financeAPI.dtos.authDtos.AuthResponseDto;
import com.example.financeAPI.dtos.authDtos.LoginRequestDto;
import com.example.financeAPI.dtos.authDtos.RegisterRequestDto;
import com.example.financeAPI.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto dto){
        AuthResponseDto auth = service.register(dto);
        return ResponseEntity.status(201).body(auth);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto dto){
        AuthResponseDto auth = service.login(dto);
        return ResponseEntity.status(200).body(auth);
    }

}
