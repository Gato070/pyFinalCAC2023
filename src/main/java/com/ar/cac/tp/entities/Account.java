package com.ar.cac.tp.entities;

import com.ar.cac.tp.entities.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="cuentas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="tipo_cuenta")
    private AccountType type;

    private String cbu;

    private String alias;

    @Column(name="monto")
    private BigDecimal amount;

    @Column(name= "fecha_creacion")
    private LocalDateTime created_at;

    @Column(name= "fecha_modificacion")
    private LocalDateTime updated_at;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> transfer;


}
