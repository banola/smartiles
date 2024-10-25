package com.example.crud.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
