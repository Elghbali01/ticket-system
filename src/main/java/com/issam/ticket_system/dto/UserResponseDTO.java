package com.issam.ticket_system.dto;

public class UserResponseDTO {
    // Ce DTO sert à ce que l’API renvoie au client.
    // Quand on fait GET /users ou GET /users/{id} l’API ne doit pas renvoyer l’Entity User, mais un DTO de réponse.
    private Long id;
    private String name;
    private String email;
    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
