package com.issam.ticket_system.controller;

import com.issam.ticket_system.dto.LoginRequestDTO;
import com.issam.ticket_system.dto.LoginResponseDTO;
import com.issam.ticket_system.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

}