package com.example.crud.model;

import com.example.crud.request.RequestHistory;
import jakarta.persistence.*;
import lombok.*;
// Esta classe faz parte da Model e é responsável pela criação da entidade "history".
// Utilizando Spring podemos automatizar algumas funções como a criação de métodos construtores e getters e setters:
@Table(name="history")
@Entity(name="history")
// Métodos Getters e Setters
@Getter
@Setter
// Cria um construtor sem parâmetros
@AllArgsConstructor
// Cria um construtor com todos os parâmetros
@NoArgsConstructor
// Garante que os objetos sejam comparados com o ID
@EqualsAndHashCode(of = "id")
public class History {
    // Esta linha a baixo garante a criação de IDs aleatórios para cada objeto utilizando o padrão UUID.
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String id_user;
    private String  id_operations;
    // Construtor que recebe um objeto RequestHistory como parâmetro, faz com que seja possível criar um novo objeto com dados
    // que já foram manipulados dentro da classe RequestHistory.
    public History(RequestHistory rh){
        this.id_user = rh.id_user();
        this.id_operations = rh.id_operations();
    }
}
