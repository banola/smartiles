package com.example.crud.repository;

import com.example.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// Esta interface é responsável por realizar a comunicação e as consultas no banco de dados.
// Essas consultas são realizadas pelas Entidades definidas nas classes de modelo.
public interface UserRepository extends JpaRepository<User, String> {
    // A query findUser encontra o usuário específico no BD que possui o email e senha informados na query.
    @Query("SELECT u FROM user u WHERE u.email = :email AND u.password = :password")
    User findUser(@Param("email") String email, @Param("password") String password);
}
