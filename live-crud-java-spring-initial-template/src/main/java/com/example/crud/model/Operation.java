package com.example.crud.model;

import com.example.crud.request.RequestOperation;
import jakarta.persistence.*;
import lombok.*;
// Esta classe faz parte da Model e é responsável pela criação da entidade "operation".
// Utilizando Spring podemos automatizar algumas funções como a criação de métodos construtores e getters e setters:
@Table(name="operations")
@Entity(name="operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Operation {
    // Esta linha a baixo garante a criação de IDs aleatórios para cada objeto utilizando o padrão UUID.
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private double loss_perc;
    private double total_tiles;
    private double final_tiles;
    // Construtor que recebe um objeto RequestOperation como parâmetro, faz com que seja possível criar um novo objeto com dados
    // que já foram manipulados dentro da classe RequestOperation.
    public Operation(RequestOperation ro){
        this.name = ro.name();
        this.loss_perc = ro.loss_perc();
        this.total_tiles = ro.total_tiles();
        this.final_tiles = ro.final_tiles();
    }
}
