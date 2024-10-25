package com.example.crud.controllers;

import com.example.crud.model.user.User;
import com.example.crud.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository repository;

    @Autowired
    public LoginController(UserRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity login(@RequestParam String email, @RequestParam String password, HttpSession session){

        User session_user = repository.findUser(email, password);
        if (session_user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos.");
        }

        session.setAttribute("user", session_user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
