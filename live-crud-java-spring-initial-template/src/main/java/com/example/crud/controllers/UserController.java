package com.example.crud.controllers;

import com.example.crud.request.RequestUser;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;
// Esta classe é responsável pelas funções CRUD referentes ao usuário:
// Algumas funções não foram implementadas pois não são necessárias para o funcionamento mais básico desta aplicação.
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository){
        this.repository = repository;
    }

    // Não implementado! -> Seria responsável por retornar todos os usuário cadastrados no banco de dados.
    // Seria utilizado por um administrador.
    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        // Utiliza a função findAll() padrão da JpaRepository:
        var AllUsers = repository.findAll();
        return ResponseEntity.ok(AllUsers);
    }

    // Retorna o usuário pelo Id do mesmo:
    @GetMapping
    public ResponseEntity getUserById(@RequestParam String id){
        // Utiliza a função padrão findById() da JpaRepository:
        var UserById = repository.findById(id);
        return ResponseEntity.ok(UserById);
    }

    // Resgistra um novo usuário no banco de dados:
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RequestUser data){
        User newUser = new User(data);
        // função save() padrão da JpaRepository:
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    // Não Implementado! -> Seria responsável por realizar atualizações no cadastro do usuário como a alteração de email.
    // Seria acessível pelo prórpio usuário assim como administradores.
    @PutMapping
    @Transactional
    public ResponseEntity alter_sessionUser(@RequestParam String name, @RequestParam String email,
                                            @RequestParam String password, HttpSession session){

        User session_user = (User) session.getAttribute("user");

        Optional<User> optionalUser = repository.findById(session_user.getId());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            return ResponseEntity.ok(user);
        }else{
            throw new EntityNotFoundException();
        }
    }

    // Responsável por retornar o usuário logado na instância em questão:
    // Necessita do parâmetro HttpeSession para poder retornar qual o usuário logado.
    @GetMapping("/currentuser")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        // Caso não exista nenhum usuário logado no momento retorna um erro.
        User session_user = (User) session.getAttribute("user");
        if (session_user != null) {
            return ResponseEntity.ok(session_user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // Não implementado! -> Seria responsável pela exclusão de um usuário do sistema.
    // Seria utilizável por usuários comuns e administradores.
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
