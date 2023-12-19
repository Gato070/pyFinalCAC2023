package com.ar.cac.tp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
//@Table(name="Transferencias") descartado por usar builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;

    //@Column(name = "monto")
    private Double amount;

   //@Column(name = "cuenta_origen_id")
    private Long accountOrigin;

    //@Column(name = "cuenta_destino_id")
    private Long accountDestination;

    //@Column(name = "fecha_transferencia")
    private LocalDateTime date;


    /* Esto es sin usar builder funciona bien
    @Column(name = "monto")
    private Double amount;

    @Column(name = "cuenta_origen_id")
    private Long accountOrigin;

    @Column(name = "cuenta_destino_id")
    private Long accountDestination;

    @Column(name = "fecha_transferencia")
    private LocalDateTime date;

     */

    @ManyToOne
    private Account creator;
}
