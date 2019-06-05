package com.example.restwithjpa.RestWithJpaProject.controller;

import com.example.restwithjpa.RestWithJpaProject.pojo.Client;
import com.example.restwithjpa.RestWithJpaProject.pojo.MoneyTransaction;
import com.example.restwithjpa.RestWithJpaProject.services.ClientRepository;
import com.example.restwithjpa.RestWithJpaProject.exceptions.ResourceNotFoundException;
import com.example.restwithjpa.RestWithJpaProject.services.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.restwithjpa.RestWithJpaProject.utils.ExceptionUtil.allowExceptionThrowing;


@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/client/{id}")
    public Optional getClientById(@PathVariable long id) {
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        return client;
    }

    @GetMapping(path = "/client/getAllClients")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @PostMapping(path = "/client")
    public ResponseEntity addClient(@Valid @RequestBody Client client){
        clientRepository.save(client);
        URI generatedUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(generatedUri).build();
    }

    @DeleteMapping(path = "/client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable long id){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/client/{id}/all-transactions")
    public List<MoneyTransaction> getAllTransactionsForClient(@PathVariable long id){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        return client.get().getMoneyTransaction();
    }

    @GetMapping(path = "/client/{id}/transactions/{amount}")
    public List<MoneyTransaction> getAllTransactionByUserBiggerThan(@PathVariable long id, @PathVariable int amount){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        return moneyTransactionRepository.transactionsByUserBiggerThan(id, amount);
    }

    @GetMapping(path = "/client/all-transactions/total/{amount}")
    public List<Client> getAllClientsWithTotalTransactions(@PathVariable int amount){
        List<Object []> clientsWIthTranscationsSum = clientRepository.getClientsWithTotalAmountOfTransactions(amount);
        List<Client> clients = new ArrayList<>();
        for (Object[] objects: clientsWIthTranscationsSum){
            Optional<Client> client = clientRepository.findById(Long.parseLong(objects[0].toString()));
            List<MoneyTransaction> moneyTransactions = new ArrayList<MoneyTransaction>(Integer.parseInt(
                    objects[1].toString()));
            client.get().setMoneyTransaction(moneyTransactions);
            clients.add(client.get());
        }
        return clients;
//        Optional<Client> client = clientRepository.findById(1l);
//        allowExceptionThrowing(client, new ResourceNotFoundException(
//                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
//        return client.get().getMoneyTransaction();
    }
}
