package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public Optional getClientById(@PathVariable long id) {
        return clientService.getClientById(id);
    }

    @GetMapping(path = "/getAllClients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping
    public ResponseEntity addClient(@Valid @RequestBody ClientDTO clientDTO){
        return clientService.addClient(clientDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable long id){
        return clientService.deleteClient(id);
    }

    @GetMapping(path = "/{id}/all-transactions")
    public List<MoneyTransaction> getAllTransactionsForClient(@PathVariable long id){
        return clientService.getAllTransactionsForClient(id);
    }

    @GetMapping(path = "/{id}/transactions/{amount}")
    public List<MoneyTransaction> getAllTransactionByUserBiggerThan(@PathVariable long id, @PathVariable int amount){
        return clientService.getAllTransactionByUserBiggerThan(id, amount);
    }

    @GetMapping(path = "/all-transactions/total/{amount}")
    public List<ClientDTO> getAllClientsWithTotalTransactions(@PathVariable int amount){
        return clientService.getAllClientsWithTotalTransactions(amount);
    }
}
