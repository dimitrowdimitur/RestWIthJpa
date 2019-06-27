package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class ClientController {

    @Autowired
    private ClientDTO clientDTO;

    @GetMapping("/client/{id}")
    public Optional getClientById(@PathVariable long id) {
        return clientDTO.getClientById(id);
    }

    @GetMapping(path = "/client/getAllClients")
    public List<Client> getAllClients(){
        return clientDTO.getAllClients();
    }

    @PostMapping(path = "/client")
    public ResponseEntity addClient(@Valid @RequestBody Client client){
        return clientDTO.addClient(client);
    }

    @DeleteMapping(path = "/client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable long id){
        return clientDTO.deleteClient(id);
    }

    @GetMapping(path = "/client/{id}/all-transactions")
    public List<MoneyTransaction> getAllTransactionsForClient(@PathVariable long id){
        return clientDTO.getAllTransactionsForClient(id);
    }

    @GetMapping(path = "/client/{id}/transactions/{amount}")
    public List<MoneyTransaction> getAllTransactionByUserBiggerThan(@PathVariable long id, @PathVariable int amount){
        return clientDTO.getAllTransactionByUserBiggerThan(id, amount);
    }

    @GetMapping(path = "/client/all-transactions/total/{amount}")
    public List<Client> getAllClientsWithTotalTransactions(@PathVariable int amount){
        return clientDTO.getAllClientsWithTotalTransactions(amount);
    }
}
