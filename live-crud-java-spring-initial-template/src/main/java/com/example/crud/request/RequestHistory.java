package com.example.crud.request;

import jakarta.validation.constraints.NotNull;
// Esta classe Record funciona como um Data Transfer Object (Objeto de transferência de dados) e serve para que exista um padrão na hora de transferir os dados
// da classe History no caso.
public record RequestHistory(
        @NotNull
        String id_user,
        @NotNull
        String id_operations
) {
}
