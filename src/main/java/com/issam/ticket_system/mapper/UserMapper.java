package com.issam.ticket_system.mapper;

import com.issam.ticket_system.dto.UserCreateDTO;
import com.issam.ticket_system.dto.UserResponseDTO;
import com.issam.ticket_system.entity.User;

public class UserMapper {

    public static User toEntity(UserCreateDTO dto){

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public static UserResponseDTO toResponseDTO(User user){

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}