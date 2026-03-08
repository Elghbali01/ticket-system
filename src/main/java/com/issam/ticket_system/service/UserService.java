package com.issam.ticket_system.service;

import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository ;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  User addUser (User u){
        return userRepository.save(u);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public  User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public  User updateUser(Long id , User user){
        if(!userRepository.existsById(id)){
            return null;
        }

        user.setId(id);
        return userRepository.save(user);
    }

    public String deleteUser(Long id){
        if(!userRepository.existsById(id)){
            return "user not found";
        }else {
            userRepository.deleteById(id);
            return "utilisateur supprimé avec succès";
        }
    }
}