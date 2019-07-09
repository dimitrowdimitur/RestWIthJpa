package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT c.id,SUM(t.amount) AS NumberOfOrders FROM MoneyTransaction t JOIN " +
            " t.client c  GROUP BY c.firstName HAVING t.amount > ?1")
    List<Object []> getClientsWithTotalAmountOfTransactions(int totalAmount);

}
