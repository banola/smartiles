package com.example.crud.request;

import jakarta.validation.constraints.NotBlank;

public record RequestTile(
        String id,
        @NotBlank
        double height,
        @NotBlank
        double width,
        @NotBlank
        double area
){}
