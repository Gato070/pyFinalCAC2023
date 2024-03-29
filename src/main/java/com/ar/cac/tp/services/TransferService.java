package com.ar.cac.tp.services;

import com.ar.cac.tp.entities.Account;
import com.ar.cac.tp.entities.Transfer;
import com.ar.cac.tp.entities.dtos.TransferDto;
import com.ar.cac.tp.exceptions.*;
import com.ar.cac.tp.mappers.TransferMapper;
import com.ar.cac.tp.repositories.AccountRepository;
import com.ar.cac.tp.repositories.TransferRepository;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    // Inyectamos TransferRepository & AccountRepository
    private final TransferRepository repository;
    private final AccountRepository repo_acc;

    public TransferService(TransferRepository repository, AccountRepository repo_acc){
        this.repository = repository;
        this.repo_acc = repo_acc;
    }

    public List<TransferDto> getTransfers(){
        return repository.findAll().stream().map(TransferMapper::transferToDto).collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id){
        Transfer transfer = repository.findById(id).orElseThrow(() ->
                new TransferNotFoundException("Transfer not found with id: " + id));
        return TransferMapper.transferToDto(transfer);
    }

    public TransferDto createTransfer(TransferDto dto){
        TransferDto resp_trans;
        resp_trans = validateTransfer(dto);

        if(!(resp_trans == null)){
            return resp_trans;
        }

        Account accountOrigin = repo_acc.findById(dto.getAccountOrigin()).get();
        Account accountDestination = repo_acc.findById(dto.getAccountDestination()).get();

        // Realizar la transferencia
        BigDecimal res_acc = accountOrigin.getAmount().subtract(BigDecimal.valueOf(dto.getAmount()));
        BigDecimal sum_acc = accountDestination.getAmount().add(BigDecimal.valueOf(dto.getAmount()));
        accountOrigin.setAmount(res_acc);
        accountDestination.setAmount(sum_acc);

        // Guardar las transferencias
        repo_acc.save(accountOrigin);
        repo_acc.save(accountDestination);

        // Setear fecha de transacción
        dto.setDate(LocalDateTime.now());

        Transfer newTransfer = TransferMapper.dtoToTransfer(dto);
        return TransferMapper.transferToDto(repository.save(newTransfer));
    }

    public TransferDto updateTransfer(Long id, TransferDto dto){
        if (repository.existsById(id)){
            TransferDto resp_trans;
            resp_trans = validateTransfer(dto);

            if (!(resp_trans == null)){
                return resp_trans;
            }

            Account accountOrigin = repo_acc.findById(dto.getAccountOrigin()).get();
            Account accountDestination = repo_acc.findById(dto.getAccountDestination()).get();

            BigDecimal res_acc = accountOrigin.getAmount().subtract(BigDecimal.valueOf(dto.getAmount()));
            BigDecimal sum_acc = accountDestination.getAmount().add(BigDecimal.valueOf(dto.getAmount()));
            accountOrigin.setAmount(res_acc);
            accountDestination.setAmount(sum_acc);

            repo_acc.save(accountOrigin);
            repo_acc.save(accountDestination);

            Transfer transferToModify = repository.findById(id).get();
            transferToModify.setAmount(dto.getAmount());
            transferToModify.setAccountOrigin(dto.getAccountOrigin());
            transferToModify.setAccountDestination(dto.getAccountDestination());
            Transfer transferModified = repository.save(transferToModify);

            return TransferMapper.transferToDto(transferModified);
        } else {
            throw new TransferNotFoundException("Transfer not found with id: " + id);
        }
    }

    public String deleteTransfer(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "La transferencia con id: " + id + " ha sido eliminada";
        }else {
            return "La transferencia con id: " + id + ", No ha sido eliminada";
        }
    }

    public TransferDto validateTransfer(TransferDto dto){

        if (!repo_acc.existsById(dto.getAccountOrigin())){
            throw new AccountNotFoundException("Account not found with id: " + dto.getAccountOrigin());
        }

        if (!repo_acc.existsById(dto.getAccountDestination())){
            throw new AccountNotFoundException("Account not found with id: " + dto.getAccountDestination());
        }

        if (dto.getAccountOrigin() == dto.getAccountDestination()){
            throw new AccountsNotEqualsException("Origin Account is equal to destination Account.");
        }

        if (dto.getAmount() == null || dto.getAmount() == 0) {
            throw new AmountZeroException("Amount cannot be empty or zero.");
        }

        Account accountOrigin = repo_acc.findById(dto.getAccountOrigin()).get();
        Account accountDestination = repo_acc.findById(dto.getAccountDestination()).get();

        if (accountOrigin.getAmount().compareTo(BigDecimal.valueOf(dto.getAmount())) < 0){
            throw new InsufficientFoundsException("Insufficient funds in the account with id: "
                    + dto.getAccountOrigin());
        }
        return null;
    }
}
