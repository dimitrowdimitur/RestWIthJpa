package com.example.restwithjpa.RestWithJpaProject.services;

import com.example.restwithjpa.RestWithJpaProject.pojo.Client;
import com.example.restwithjpa.RestWithJpaProject.pojo.MoneyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoneyTransactionRepository extends JpaRepository<MoneyTransaction, Long> {
    @Query(value = "SELECT * FROM MONEY_TRANSACTION t WHERE t.CLIENT_ID = ?1 AND t.AMOUNT > ?2", nativeQuery = true)
    List<MoneyTransaction> transactionsByUserBiggerThan(Long id, int amount);

}
