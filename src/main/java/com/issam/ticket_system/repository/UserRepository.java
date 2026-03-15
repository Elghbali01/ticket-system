package com.issam.ticket_system.repository;

import com.issam.ticket_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User , Long> {
    /*
    Grace  a jpaRepository on obtient :
    .save() => créer ou modifier
    .findById() => chercher par id
    .findAll() => récupérer tous
    .deleteById() => supprimer
     */
    User findByEmail(String email);

}
