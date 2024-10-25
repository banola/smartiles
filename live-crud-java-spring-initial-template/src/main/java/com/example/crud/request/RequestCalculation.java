package com.example.crud.request;

import jakarta.validation.constraints.NotNull;

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
