package com.ar.cac.tp.controllers;

import com.ar.cac.tp.entities.User;
import com.ar.cac.tp.entities.dtos.UserDto;
import com.ar.cac.tp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    //Por cada metodo http que ustedes quieren implementar o permitir vamos a tener un metodo
    //en su controlador
    //Metodos HTTP permitidos
    //POST, GET (2), PUT, PATCH, DELETE
    // Estructura de datos para REST: ResponseEntity<>

   // Necesito obtener una instancia (Objeto) del servicio de usuarios
    @Autowired
    private UserService service;



    //Obtener una lista de usuarios registrados
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
        //return List.of( "Cristian", "Maria", "Julieta", "Marcos");
    }
    //Obtener la info de un solo usuario
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    //Crear/Registrar un usuario
     @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    //Modificar TOTALMENTE un usuario (PUT)...metodo
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateFullUser (@PathVariable Long id, @RequestBody UserDto user) {

        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
    }



    /*
    //Modificar PARCIALMENTE un usuario (PATCH).....metodo
    @PatchMapping(value = "/{id}")
    public String updateParcialUser () {
        return "";
    }*/

    //Eliminar un usuario
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(id));
    }

}
