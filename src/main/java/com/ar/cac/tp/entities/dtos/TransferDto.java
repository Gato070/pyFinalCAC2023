package com.ar.cac.tp.entities.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDto {

    private  Long id;

    private Double amount;

    private Long accountOrigin;

    private Long accountDestination;

    private LocalDateTime date;
}
