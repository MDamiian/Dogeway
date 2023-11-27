package com.dogeway.dw.mascota;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePetDTO(
        @NotNull
        Long idmascota,
        @NotBlank
        String nombre,
        @NotNull
        Animal animal,
        @NotNull
        UtilidadDeMascota utilidadDeMascota,
        @NotNull
        Tamano tamano,
        @NotBlank
        String descripcion,
        @NotNull
        Personalidad personalidad,
        @NotBlank
        String foto,
        @NotNull
        boolean genero
) {
}
