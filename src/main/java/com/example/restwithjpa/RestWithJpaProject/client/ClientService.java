package com.example.restwithjpa.RestWithJpaProject.client;

import com.example.restwithjpa.RestWithJpaProject.exceptions.ResourceNotFoundException;
import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransaction;
import com.example.restwithjpa.RestWithJpaProject.transaction.MoneyTransactionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Lists;
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
public class ClientService {

    private final ClientRepository clientRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;
    private final MessageSource messageSource;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, MoneyTransactionRepository moneyTransactionRepository, MessageSource messageSource, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.moneyTransactionRepository = moneyTransactionRepository;
        this.messageSource = messageSource;
        this.modelMapper = modelMapper;
    }

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
        return Lists.from(clientRepository.findAll().iterator());
    }

    /**
     * @param clientDTO
     * @return
     */
    public ResponseEntity addClient(@Valid @RequestBody ClientDTO clientDTO){
        clientRepository.save(convertToEntity(clientDTO));
        URI generatedUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId()).toUri();
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
    public List<ClientDTO> getAllClientsWithTotalTransactions(@PathVariable int amount){
        List<Object []> clientsWIthTranscationsSum = clientRepository.getClientsWithTotalAmountOfTransactions(amount);
        List<ClientDTO> clientsDto = new ArrayList<>();
        for (Object[] objects: clientsWIthTranscationsSum){
            Optional<Client> client = clientRepository.findById(Long.parseLong(objects[0].toString()));
            client.get().setAmountOfAllTransactions(Integer.parseInt(
                    objects[1].toString()));
            clientsDto.add(convertToDto(client.get()));
        }
        return clientsDto;
    }


    /**
     * @param clientDTO
     * @return
     */
    private Client convertToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
        }

    /**
     * @param client
     * @return
     */
    private ClientDTO convertToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
}
