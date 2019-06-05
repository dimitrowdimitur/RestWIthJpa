package com.example.restwithjpa.RestWithJpaProject.controller;

import com.example.restwithjpa.RestWithJpaProject.exceptions.ResourceNotFoundException;
import com.example.restwithjpa.RestWithJpaProject.pojo.Client;
import com.example.restwithjpa.RestWithJpaProject.pojo.MoneyTransaction;
import com.example.restwithjpa.RestWithJpaProject.services.ClientRepository;
import com.example.restwithjpa.RestWithJpaProject.services.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static com.example.restwithjpa.RestWithJpaProject.utils.ExceptionUtil.allowExceptionThrowing;

@RestController
public class MoneyTransactionController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    @GetMapping("/transaction/{id}")
    public Optional getTransaction(@PathVariable long id){
        Optional<MoneyTransaction> transaction = moneyTransactionRepository.findById(id);
        allowExceptionThrowing(transaction, new ResourceNotFoundException(
                messageSource.getMessage("transaction.not.found.exception", null, LocaleContextHolder.getLocale())));
        return transaction;
    }

    @PostMapping("/transaction/user/{id}")
    public ResponseEntity addTransaction(@PathVariable long id, @Valid @RequestBody MoneyTransaction moneyTransaction){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        moneyTransaction.setClient(client.get());
        moneyTransactionRepository.save(moneyTransaction);
        URI generatedUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(moneyTransaction.getId()).toUri();
        return ResponseEntity.created(generatedUri).build();
    }
}
