package com.issam.ticket_system.service;

import com.issam.ticket_system.dto.UserCreateDTO;
import com.issam.ticket_system.dto.UserResponseDTO;
import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void addUser_shouldCreateUser() {

        String name = "sampleUser";
        String email = "user@example.com";
        String password = "passwordValue";

        UserCreateDTO dto = new UserCreateDTO(name, email, password);

        when(passwordEncoder.encode(password))
                .thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(name);
        savedUser.setEmail(email);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        UserResponseDTO result = userService.addUser(dto);

        assertNotNull(result);
        assertEquals(name, result.getName());

        verify(userRepository).save(any(User.class));
    }
}