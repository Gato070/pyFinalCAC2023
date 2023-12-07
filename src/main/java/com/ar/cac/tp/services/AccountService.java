package com.ar.cac.tp.services;

import com.ar.cac.tp.entities.Account;
import com.ar.cac.tp.entities.dtos.AccountDto;
import com.ar.cac.tp.entities.enums.AccountType;
import com.ar.cac.tp.mappers.AccountMapper;
import com.ar.cac.tp.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    //inyectar el repositorio dentro del servicio
    private final AccountRepository repository;

    private AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public AccountDto getAccountById(Long id) {
        Account acc = repository.findById(id).get();
        return AccountMapper.accountToDto(acc);

    }

    public List<AccountDto> getAccounts() {
        return repository.findAll().stream().map(AccountMapper::accountToDto).collect(Collectors.toList());
    }

    public AccountDto createAccount(AccountDto dto) {
        //TODO refactor para crear diferentes tipos de cuenta inicial
        dto.setAmount(BigDecimal.ZERO);
        dto.setType(AccountType.CAJA_AHORRO_PESOS);
        Account newAccount = AccountMapper.dtoToAccount(dto);
        return AccountMapper.accountToDto(repository.save(newAccount));
    }

    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)){
            Account acc = repository.findById(id).get();

            if (dto.getAlias() != null){
                acc.setAlias(dto.getAlias());
            }

            if (dto.getType() != null){
                acc.setType(dto.getType());
            }

            if (dto.getCbu() != null){
                acc.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null){
                acc.setAmount(dto.getAmount());
            }

            return AccountMapper.accountToDto(acc);

        }else {
            return null;
        }

    }

    public String deleteAccount(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Cuenta eleminada";
        } else {

            return "No se puede eliminar la cuenta";
        }

    }
}
