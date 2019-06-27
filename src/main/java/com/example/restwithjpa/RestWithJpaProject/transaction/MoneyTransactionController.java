package com.example.restwithjpa.RestWithJpaProject.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class MoneyTransactionController {

    @Autowired
    private MoneyTransactionDTO moneyTransactionDTO;

    @GetMapping("/transaction/{id}")
    public Optional getTransaction(@PathVariable long id){
        return moneyTransactionDTO.getTransaction(id);
    }

    @PostMapping("/transaction/user/{id}")
    public ResponseEntity addTransaction(@PathVariable long id, @Valid @RequestBody MoneyTransaction moneyTransaction){
        return moneyTransactionDTO.addTransaction(id, moneyTransaction);
    }
}
