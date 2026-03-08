package com.issam.ticket_system.mapper;

import com.issam.ticket_system.dto.UserCreateDTO;
import com.issam.ticket_system.dto.UserResponseDTO;
import com.issam.ticket_system.entity.User;

    public class UserMapper {

    // DTO → Entity
    public static User toEntity(UserCreateDTO dto) {
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    // Entity → ResponseDTO
    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}