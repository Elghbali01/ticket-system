package com.issam.ticket_system.service;

import com.issam.ticket_system.dto.UserCreateDTO;
import com.issam.ticket_system.dto.UserResponseDTO;
import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.mapper.UserMapper;
import com.issam.ticket_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE USER
    public UserResponseDTO addUser(UserCreateDTO dto) {

        User user = UserMapper.toEntity(dto);

        // encoder le mot de passe
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponseDTO(savedUser);
    }

    // GET ALL USERS
    public List<UserResponseDTO> getUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET USER BY ID
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return UserMapper.toResponseDTO(user);
    }

    // UPDATE USER
    public UserResponseDTO updateUser(Long id, UserCreateDTO dto) {

        if (!userRepository.existsById(id)) {
            return null;
        }

        User user = UserMapper.toEntity(dto);
        user.setId(id);

        // encoder le mot de passe aussi lors d'une modification
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponseDTO(updatedUser);
    }

    // DELETE USER
    public String deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            return "user not found";
        }

        userRepository.deleteById(id);

        return "utilisateur supprimé avec succès";
    }
}