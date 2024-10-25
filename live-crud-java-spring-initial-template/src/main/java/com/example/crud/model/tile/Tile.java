package com.example.crud.model.tile;

import com.example.crud.request.RequestTile;
import jakarta.persistence.*;
import lombok.*;

@Table(name="tile")
@Entity(name="tile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tile {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double height;
    private double width;
    private double area;

    public Tile(RequestTile rt){
        this.height = rt.height();
        this.width = rt.width();
        this.area = rt.area();
    }
}
