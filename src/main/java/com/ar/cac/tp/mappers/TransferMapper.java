package com.ar.cac.tp.mappers;

import com.ar.cac.tp.entities.Transfer;
import com.ar.cac.tp.entities.dtos.TransferDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    public Transfer dtoToTransfer(TransferDto dto){
        return Transfer.builder()
                .amount(dto.getAmount())
                .accountOrigin(dto.getAccountOrigin())
                .accountDestination(dto.getAccountDestination())
                .date(dto.getDate())
                .build();

    }

    public TransferDto transferToDto(Transfer transfer){
        return TransferDto.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .accountOrigin(transfer.getAccountOrigin())
                .accountDestination(transfer.getAccountDestination())
                .date(transfer.getDate())
                .build();

    }
    /*
    public Transfer dtoToTransfer(TransferDto dto){
        Transfer transfer = new Transfer();
        transfer.setAmount(dto.getAmount());
        transfer.setAccountOrigin(dto.getAccountOrigin());
        transfer.setAccountDestination(dto.getAccountDestination());
        transfer.setDate(dto.getDate());
        return transfer;
    }

    public TransferDto transferToDto(Transfer transfer){
        TransferDto dto = new TransferDto();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());
        dto.setAccountOrigin(transfer.getAccountOrigin());
        dto.setAccountDestination(transfer.getAccountDestination());
        dto.setDate(transfer.getDate());
        return dto;
    }
    */
}
