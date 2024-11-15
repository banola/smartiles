package com.example.crud.model;

import com.example.crud.request.RequestUser;
import jakarta.persistence.*;
import lombok.*;
// Esta classe faz parte da Model e é responsável pela criação da entidade "user".
// Utilizando Spring podemos automatizar algumas funções como a criação de métodos construtores e getters e setters:
@Table(name="users")
@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    // Esta linha a baixo garante a criação de IDs aleatórios para cada objeto utilizando o padrão UUID.
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    // Construtor que recebe um objeto RequestUser como parâmetro, faz com que seja possível criar um novo objeto com dados
    // que já foram manipulados dentro da classe RequestUser.
    public User(RequestUser ru){
        this.name = ru.name();
        this.email = ru.email();
        this.password = ru.password();
    }
}
