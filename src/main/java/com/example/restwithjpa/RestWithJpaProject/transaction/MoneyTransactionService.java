package com.example.restwithjpa.RestWithJpaProject.transaction;

import com.example.restwithjpa.RestWithJpaProject.client.Client;
import com.example.restwithjpa.RestWithJpaProject.client.ClientRepository;
import com.example.restwithjpa.RestWithJpaProject.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static com.example.restwithjpa.RestWithJpaProject.utils.ExceptionUtil.allowExceptionThrowing;

@Service
public class MoneyTransactionService {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param id
     * @return
     */
    public Optional getTransaction(@PathVariable long id){
        Optional<MoneyTransaction> transaction = moneyTransactionRepository.findById(id);
        allowExceptionThrowing(transaction, new ResourceNotFoundException(
                messageSource.getMessage("transaction.not.found.exception", null, LocaleContextHolder.getLocale())));
        return transaction;
    }

    /**
     * @param id
     * @param moneyTransactionDTO
     * @return
     */
    public ResponseEntity addTransaction(@PathVariable long id, @Valid @RequestBody MoneyTransactionDTO moneyTransactionDTO){
        Optional<Client> client = clientRepository.findById(id);
        allowExceptionThrowing(client, new ResourceNotFoundException(
                messageSource.getMessage("client.not.found.exception", null, LocaleContextHolder.getLocale())));
        moneyTransactionDTO.setClient(client.get());
        moneyTransactionRepository.save(convertToEntity(moneyTransactionDTO));
        URI generatedUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(moneyTransactionDTO.getId()).toUri();
        return ResponseEntity.created(generatedUri).build();
    }

    /**
     * @param moneyTransactionDTO
     * @return
     */
    private MoneyTransaction convertToEntity(MoneyTransactionDTO moneyTransactionDTO) {
        return modelMapper.map(moneyTransactionDTO,  MoneyTransaction.class);
    }
}
