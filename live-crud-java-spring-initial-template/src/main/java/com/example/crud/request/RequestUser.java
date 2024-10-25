package com.example.crud.request;

import jakarta.validation.constraints.NotBlank;

public record RequestUser(
        String id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
){}
