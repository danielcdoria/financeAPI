package com.example.financeAPI.controller;

import com.example.financeAPI.dtos.transactionDtos.TransactionRequestDto;
import com.example.financeAPI.dtos.transactionDtos.TransactionResponseDto;
import com.example.financeAPI.models.Transaction;
import com.example.financeAPI.models.User;
import com.example.financeAPI.service.TransactionService;
import jdk.jfr.Category;
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
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable Long id){
        TransactionResponseDto transaction = service.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id){
        String message = service.remove(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/transactions/type")
    public ResponseEntity<List<TransactionResponseDto>> findByType(@RequestParam Transaction.Type type){
        List<TransactionResponseDto> list = service.findByType(type);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/transactions/category")
    public ResponseEntity<List<TransactionResponseDto>> findByCategory(@RequestParam String category){
        List<TransactionResponseDto> list = service.findByCategory(category);
        return ResponseEntity.ok(list);
    }
}
