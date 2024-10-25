package com.example.crud.controllers;

import com.example.crud.request.RequestUser;
import com.example.crud.model.user.User;
import com.example.crud.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        var AllUsers = repository.findAll();
        return ResponseEntity.ok(AllUsers);
    }

    @GetMapping
    public ResponseEntity getUserById(@RequestParam String id){
        var UserById = repository.findById(id);
        return ResponseEntity.ok(UserById);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RequestUser data){
        User newUser = new User(data);
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

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

    @GetMapping("/currentuser")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        User session_user = (User) session.getAttribute("user");
        if (session_user != null) {
            return ResponseEntity.ok(session_user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
