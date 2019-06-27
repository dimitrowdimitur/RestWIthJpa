package com.example.restwithjpa.RestWithJpaProject.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoneyTransactionRepository extends JpaRepository<MoneyTransaction, Long> {
    @Query(value = "SELECT t FROM MoneyTransaction t WHERE t.client.id = ?1 AND t.amount > ?2")
    List<MoneyTransaction> transactionsByUserBiggerThan(Long id, int amount);

}
