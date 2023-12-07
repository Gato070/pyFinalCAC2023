package com.ar.cac.tp.entities.dtos;

import com.ar.cac.tp.entities.enums.AccountType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private AccountType type;

    private String cbu;

    private String alias;

    private BigDecimal amount;

}
