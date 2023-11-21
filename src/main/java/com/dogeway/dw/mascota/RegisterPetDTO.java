package com.dogeway.dw.mascota;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterPetDTO(
        @NotBlank
        String nombre,
        @NotNull
        Animal animal,
        @NotNull
        Tamano tamano,
        @NotBlank
        String descripcion,
        @NotNull
        Personalidad personalidad,
        @NotBlank
        String foto,
        @NotNull
        boolean genero,
        @NotNull
        Long id
) {
}
