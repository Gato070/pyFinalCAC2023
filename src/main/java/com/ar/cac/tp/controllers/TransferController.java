package com.ar.cac.tp.controllers;

import com.ar.cac.tp.entities.dtos.TransferDto;
import com.ar.cac.tp.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService service;

    // Obtener una lista de transferencias
    @GetMapping
    public ResponseEntity<List<TransferDto>> getTransfers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    // Obtener la info de una sola transferencia
    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    // Crear/Registrar una transferencia
    @PostMapping
    public  ResponseEntity<TransferDto> createTransfer(@RequestBody TransferDto transfer){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(transfer));
    }

    // Modificar una transferencia (PUT)
    @PutMapping(value = "/{id}")
    public ResponseEntity<TransferDto> updateTransfer(@PathVariable Long id, @RequestBody TransferDto transferDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTransfer(id, transferDto));
    }

    // Eliminar una transferencia
    // TODO: borrado lógico
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteTransfer(id));
    }
}
