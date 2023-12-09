package com.ar.cac.tp.services;

import com.ar.cac.tp.entities.Transfer;
import com.ar.cac.tp.entities.dtos.TransferDto;
import com.ar.cac.tp.mappers.TransferMapper;
import com.ar.cac.tp.repositories.AccountRepository;
import com.ar.cac.tp.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

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
}
