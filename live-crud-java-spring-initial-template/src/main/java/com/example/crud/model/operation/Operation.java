package com.example.crud.model.operation;

import com.example.crud.request.RequestOperation;
import jakarta.persistence.*;
import lombok.*;

@Table(name="operations")
@Entity(name="operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Operation {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private double loss_perc;
    private double total_tiles;
    private double final_tiles;

    public Operation(RequestOperation ro){
        this.name = ro.name();
        this.loss_perc = ro.loss_perc();
        this.total_tiles = ro.total_tiles();
        this.final_tiles = ro.final_tiles();
    }
}
