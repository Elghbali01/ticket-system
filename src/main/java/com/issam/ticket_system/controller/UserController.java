package com.issam.ticket_system.controller;

import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // cette classe peut recevoir des requêtes HTTP
@RequestMapping("/users") //définit la base URL du controller.
public class UserController {
    private UserService userService; // variable pour utiliser le service.
    public UserController (UserService userService){
        // injecter UserService dans UserController
        this.userService=userService;
    }
    @PostMapping
    public User addUser(@Valid @RequestBody User user){
        // valid => avant dexucuter la methode verifier les regle dans class user
        return userService.addUser((user));
    }
    @GetMapping
    public List<User> getUsers(){
        return  userService.getUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id ,@RequestBody User user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
