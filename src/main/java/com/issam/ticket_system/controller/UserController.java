package com.issam.ticket_system.controller;

import com.issam.ticket_system.dto.UserCreateDTO;
import com.issam.ticket_system.dto.UserResponseDTO;
import com.issam.ticket_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public UserResponseDTO addUser(@Valid @RequestBody UserCreateDTO dto){
        return userService.addUser(dto);
    }

    // GET ALL
    @GetMapping
    public List<UserResponseDTO> getUsers(){
        return userService.getUsers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,
                                      @Valid @RequestBody UserCreateDTO dto){
        return userService.updateUser(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}