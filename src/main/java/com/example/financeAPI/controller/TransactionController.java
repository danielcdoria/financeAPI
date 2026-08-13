package com.example.financeAPI.controller;

import com.example.financeAPI.dtos.transactionDtos.TransactionRequestDto;
import com.example.financeAPI.dtos.transactionDtos.TransactionResponseDto;
import com.example.financeAPI.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService service;
    public TransactionController(TransactionService service){
        this.service = service;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponseDto>> list(){
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponseDto> create(@RequestBody TransactionRequestDto dto){
        TransactionResponseDto transaction = service.createTransaction(dto);
        return ResponseEntity.status(201).body(transaction);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionResponseDto> findTransaction(@PathVariable Long id){
        
    }
}
