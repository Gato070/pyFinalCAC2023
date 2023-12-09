package com.ar.cac.tp.mappers;

import com.ar.cac.tp.entities.Transfer;
import com.ar.cac.tp.entities.dtos.TransferDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

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

}
