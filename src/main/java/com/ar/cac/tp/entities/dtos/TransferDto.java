package com.ar.cac.tp.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private  Long id;

    private Double amount;

    private Long accountOrigin;

    private Long accountDestination;

    private LocalDateTime date;
}
