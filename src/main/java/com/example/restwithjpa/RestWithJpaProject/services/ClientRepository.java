package com.example.restwithjpa.RestWithJpaProject.services;

import com.example.restwithjpa.RestWithJpaProject.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT c.ID,SUM(t.AMOUNT) AS NumberOfOrders FROM MONEY_TRANSACTION t LEFT JOIN CLIENT" +
            " c ON t.CLIENT_ID  = c.ID GROUP BY c.FIRST_NAME HAVING SUM(AMOUNT) > ?1" , nativeQuery = true)
    List<Object []> getClientsWithTotalAmountOfTransactions(int totalAmount);

}
