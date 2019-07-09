package com.example.restwithjpa.RestWithJpaProject.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoneyTransactionRepository extends CrudRepository<MoneyTransaction, Long> {
    @Query(value = "SELECT t FROM MoneyTransaction t WHERE t.client.id = ?1 AND t.amount > ?2")
    List<MoneyTransaction> transactionsByUserBiggerThan(Long id, int amount);

}
