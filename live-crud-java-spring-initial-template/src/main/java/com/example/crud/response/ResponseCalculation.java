package com.example.crud.response;
// Apenas uma simples classe Record para que possa ser possível retornar dois valores em uma única consulta.
// No caso os valores: total_tiles e final_tiles.
public record ResponseCalculation(
        double total_tiles,
        double final_tiles
){}
