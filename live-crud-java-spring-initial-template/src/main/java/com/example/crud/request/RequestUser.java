package com.example.crud.request;

import jakarta.validation.constraints.NotBlank;
// Esta classe Record funciona como um Data Transfer Object (Objeto de transferência de dados)
// e serve para que exista um padrão na hora de transferir os dados
// da classe User no caso.
public record RequestUser(
        String id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
){}
