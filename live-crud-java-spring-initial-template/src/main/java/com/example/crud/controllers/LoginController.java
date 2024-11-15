package com.example.crud.controllers;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Esta classe é responsável pelo Login e Logout do usuário no sistema.

// O caminho para acessar esta página é "/login".
@RestController
@RequestMapping("/login")
public class LoginController {
    // Nesta classe precisamos injetar os valores presentes na UserRepository.
    @Autowired
    private UserRepository repository;

    @Autowired
    public LoginController(UserRepository repository){
        this.repository = repository;
    }
    // Ao haver uma requisição do tipo POST sem nenhum especificação é chamada a função "login".
    // O metodo login necessita dos parâmetros email e senha, além da sessão atual presente em "HttpSession session".
    @PostMapping
    public ResponseEntity login(@RequestParam String email, @RequestParam String password, HttpSession session){
        // A classe UserRepository é responsável pela busca do usuário que está tentando logar no banco de dados.
        // Caso seja retornado um objeto User com email e senha o usuário é logado.
        User session_user = repository.findUser(email, password);
        // Caso a busca retorne um valor nulo para o objeto session_user é retornado um erro e o login não é executado.
        if (session_user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos.");
        }

        session.setAttribute("user", session_user);
        return ResponseEntity.ok().build();
    }

    // Ao haver uma requisição do tipo POST com o caminho "/logout" este metodo é invocado:
    // O metodo logout apenas necessita da session atual. Aqui apenas invalidamos o valor atual da session para resetá-la.
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
