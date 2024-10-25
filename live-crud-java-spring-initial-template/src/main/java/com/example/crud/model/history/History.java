package com.example.crud.model.history;

import com.example.crud.request.RequestHistory;
import jakarta.persistence.*;
import lombok.*;

@Table(name="history")
@Entity(name="history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class History {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String id_user;
    private String  id_operations;

    public History(RequestHistory rh){
        this.id_user = rh.id_user();
        this.id_operations = rh.id_operations();
    }
}
