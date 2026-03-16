package com.issam.ticket_system.service;

import com.issam.ticket_system.dto.LoginRequestDTO;
import com.issam.ticket_system.dto.LoginResponseDTO;
import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.repository.UserRepository;
import com.issam.ticket_system.security.JwtService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void login_shouldReturnToken() {

        String email = "user@example.com";
        String rawPassword = "passwordValue";
        String encodedPassword = "encodedValue";
        String token = "generated-token";

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail(email);
        request.setPassword(rawPassword);

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        when(userRepository.findByEmail(email))
                .thenReturn(user);

        when(passwordEncoder.matches(rawPassword, encodedPassword))
                .thenReturn(true);

        when(jwtService.generateToken(email))
                .thenReturn(token);

        LoginResponseDTO response = authService.login(request);

        assertNotNull(response);
        assertEquals(token, response.getToken());

        verify(userRepository).findByEmail(email);
        verify(jwtService).generateToken(email);
    }
}