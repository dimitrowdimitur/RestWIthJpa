package com.example.restwithjpa.RestWithJpaProject.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transaction")
public class MoneyTransactionController {

    @Autowired
    private MoneyTransactionService moneyTransactionService;

    @GetMapping("/{id}")
    public Optional getTransaction(@PathVariable long id){
        return moneyTransactionService.getTransaction(id);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity addTransaction(@PathVariable long id, @Valid @RequestBody MoneyTransactionDTO moneyTransactionDTO){
        return moneyTransactionService.addTransaction(id, moneyTransactionDTO);
    }
}
