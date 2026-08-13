package com.example.financeAPI.service;

import com.example.financeAPI.dtos.transactionDtos.TransactionRequestDto;
import com.example.financeAPI.dtos.transactionDtos.TransactionResponseDto;
import com.example.financeAPI.models.Transaction;
import com.example.financeAPI.models.User;
import com.example.financeAPI.repositories.TransactionRepository;
import com.example.financeAPI.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final UserRepository userRepository;
    public TransactionService(TransactionRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private User getLoggedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    private TransactionResponseDto convertToDto(Transaction transaction){
        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getCategory(),
                transaction.getDate()
        );
    }

    public List<TransactionResponseDto> list(){
        User user = getLoggedUser();
        return repository.findByUser(user)
                .stream()
                .map(this::convertToDto)
                .toList();

    }

    public TransactionResponseDto createTransaction(TransactionRequestDto dto){
        User user = getLoggedUser();
        Transaction transaction = new Transaction(
                dto.getTitle(),
                dto.getAmount(),
                dto.getType(),
                dto.getCategory(),
                dto.getDate()
        );
        transaction.setUser(user);
        repository.save(transaction);
        return convertToDto(transaction);
    }

    public TransactionResponseDto findById(Long id){
        User user = getLoggedUser();
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        if (!transaction.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Access denied");
        }
        return convertToDto(transaction);
    }

    public String remove(Long id){
        User user = getLoggedUser();
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found."));
        if (!transaction.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Access denied.");
        }
        repository.delete(transaction);
        return "The transaction was removed successfully!";
    }

    public List<TransactionResponseDto> findByType(Transaction.Type type){
        User user = getLoggedUser();
        return repository.findByUserAndType(user, type)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<TransactionResponseDto> findByCategory(String category){
        User user = getLoggedUser();
        return repository.findByUserAndCategory(user, category)
                .stream()
                .map(this::convertToDto)
                .toList();
    }


}
