package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.exceptions.ResourceNotFoundException;
import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransaction;
import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.restwithjpa.RestWithJpaProject.utils.ExceptionUtil.allowExceptionThrowing;

@Service
public class ClientDTO {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;
    @Autowired
    private MessageSource messageSource;

    /**
     * @param id
     * @return
     */
    public Optional getClientById(long id){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        return client;
    }


    /**
     * @return
     */
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    /**
     * @param client
     * @return
     */
    public ResponseEntity addClient(@Valid @RequestBody Client client){
        clientRepository.save(client);
        URI generatedUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(generatedUri).build();
    }

    /**
     * @param id
     * @return
     */
    public ResponseEntity<Object> deleteClient(@PathVariable long id){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @return
     */
    public List<MoneyTransaction> getAllTransactionsForClient(@PathVariable long id){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        return client.get().getMoneyTransaction();
    }

    /**
     * @param id
     * @param amount
     * @return
     */
    public List<MoneyTransaction> getAllTransactionByUserBiggerThan(@PathVariable long id, @PathVariable int amount){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        return moneyTransactionRepository.transactionsByUserBiggerThan(id, amount);
    }

    /**
     * @param amount
     * @return
     */
    public List<Client> getAllClientsWithTotalTransactions(@PathVariable int amount){
        List<Object []> clientsWIthTranscationsSum = clientRepository.getClientsWithTotalAmountOfTransactions(amount);
        List<Client> clients = new ArrayList<>();
        for (Object[] objects: clientsWIthTranscationsSum){
            Optional<Client> client = clientRepository.findById(Long.parseLong(objects[0].toString()));
            client.get().setAmountOfAllTransactions(Integer.parseInt(
                    objects[1].toString()));
            clients.add(client.get());
        }
        return clients;
    }

}
