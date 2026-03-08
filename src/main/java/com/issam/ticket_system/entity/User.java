package com.issam.ticket_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required") // impossible de laisser ce champ vide
    private String name;

    @Column(unique = true)
    @Email(message = "email must be valid") // format d'email
    @NotBlank(message = "email is required") //impossible de laisser le champ vide
    private String email;

    @Size(min = 6, message = "password must have at least 6 characters") // au mois 6 caractère
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets ;
    public User(){
        // constructeur vide pour Hibernate
    }

    public User(Long id, String password, String email, String name, List<Ticket> tickets) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.tickets=tickets;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
