package com.example.crud.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// Esta classe Record funciona como um Data Transfer Object (Objeto de transferência de dados)
// e serve para que exista um padrão na hora de transferir os dados
// da classe Operation no caso.
public record RequestOperation(
        String id,
        @NotBlank
        String name,
        @NotNull
        double loss_perc,
        @NotNull
        double total_tiles,
        @NotNull
        double final_tiles
){}
