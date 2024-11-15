package com.example.crud.request;

import jakarta.validation.constraints.NotNull;
// Esta classe Record funciona como um Data Transfer Object (Objeto de transferência de dados) e serve para que exista um padrão na hora de transferir os dados
// da classe Calculation no caso.
public record RequestCalculation (
        @NotNull
        double room_height,
        @NotNull
        double room_width,
        @NotNull
        double tile_height,
        @NotNull
        double tile_width,
        @NotNull
        double loss_perc
){}
