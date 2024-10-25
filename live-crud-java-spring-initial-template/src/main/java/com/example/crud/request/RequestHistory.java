package com.example.crud.request;

import jakarta.validation.constraints.NotNull;

public record RequestHistory(
        @NotNull
        String id_user,
        @NotNull
        String id_operations
) {
}
