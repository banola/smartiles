package com.example.crud.repository;

import com.example.crud.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM user u WHERE u.email = :email AND u.password = :password")
    User findUser(@Param("email") String email, @Param("password") String password);
}
