package com.issam.ticket_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {

    //sert pour recevoir les données POST /users

    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "email must be valid")
    @NotBlank(message = "email is required")
    private String email;

    @Size(min = 6, message = "password must have at least 6 characters")
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}