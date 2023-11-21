package com.dogeway.dw.mascota;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterPetDTO(
        @NotBlank
        String nombre,
        @NotNull
        Animal animal,
        @NotNull
        float peso,
        @NotNull
        Tamano tamano,
        @NotBlank
        String descripcion,
        @NotNull
        Personalidad personalidad,
        @NotBlank
        String foto,
        boolean genero,
        @NotNull
        Long id
) {
}
