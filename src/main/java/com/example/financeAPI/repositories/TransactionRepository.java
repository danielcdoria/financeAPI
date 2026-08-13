package com.example.financeAPI.repositories;

import com.example.financeAPI.models.Transaction;
import com.example.financeAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByUserAndType(User user, Transaction.Type type);
    List<Transaction> findByUserAndCategory(User user, String category);
}
