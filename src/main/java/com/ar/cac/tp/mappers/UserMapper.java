package com.ar.cac.tp.mappers;

import com.ar.cac.tp.entities.User;
import com.ar.cac.tp.entities.dtos.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMapper {

    public static User dtoToUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setDni(dto.getDni());
        user.setBirthday_date(dto.getBirthday_date());
        user.setEmail(dto.getEmail());
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
        return user;
    }

    public static UserDto userToDto (User user){
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setId(user.getId());
        dto.setAddress(user.getAddress());
        dto.setEmail(user.getEmail());
        dto.setDni(user.getDni());
        dto.setBirthday_date(user.getBirthday_date());
        dto.setCreated_at(user.getCreated_at());
        dto.setUpdated_at(user.getUpdated_at());

        return dto;
    }
}
