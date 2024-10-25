package com.example.crud.model.user;

import com.example.crud.request.RequestUser;
import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;

    public User(RequestUser ru){
        this.name = ru.name();
        this.email = ru.email();
        this.password = ru.password();
    }
}
