package com.ar.cac.tp.services;

import com.ar.cac.tp.entities.User;
import com.ar.cac.tp.entities.dtos.UserDto;
import com.ar.cac.tp.mappers.UserMapper;
import com.ar.cac.tp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getUsers(){
        List<User> users = repository.findAll();

        return users;
    }

    public UserDto getUserById(Long id){
        User user = repository.findById(id).get();
        user.setPassword("******");
        return UserMapper.userToDto(user);
    }

    public UserDto createUser(UserDto user){
        User entity = UserMapper.dtoToUser(user);
        User entitySaved = repository.save(entity);
        user = UserMapper.userToDto(entitySaved);
        user.setPassword("******");
        return user;
    }

    public String deleteUser(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        }else {
            return "El usuario con id: " + id + " No ha sido eliminado";
            //throw new UserNotExistsException( "El usuario con id: " + id + " No ha sido eliminado");
        }
    }

    public UserDto updateUser(Long id, UserDto dto) {

        if (repository.existsById(id)) {
            User userToModify = repository.findById(id).get(); // Al agregarle el get() no trae el Optional
            //Validar que datos no vienen en null para setearlos al objeto ya creado.
            //Logica del Patch
            if (dto.getUsername() != null) {
                userToModify.setUsername(dto.getUsername());
            }

            if (dto.getDni() != null) {
                userToModify.setDni(dto.getDni());
            }

            if (dto.getEmail() != null) {
                userToModify.setEmail(dto.getEmail());
            }

            if (dto.getBirthday_date() != null) {
                userToModify.setBirthday_date(dto.getBirthday_date());
            }

            if (dto.getId() != null) {
                userToModify.setId(dto.getId());
            }

            if (dto.getAddress() != null) {
                userToModify.setAddress(dto.getAddress());
            }

            if (dto.getPassword() != null) {
                userToModify.setPassword(dto.getPassword());
            }

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }

        return null;
    }

    //Validar que existen usuarios unicos por mail
    public String validateUserByEmail(UserDto dto){
        if ( repository.findByEmail(dto.getEmail())){
            return null;
        }else {
            User user = repository.save(UserMapper.dtoToUser(dto));
            return "Usuario creado exitosamente";
        }
    }

}
