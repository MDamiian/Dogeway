package com.dogeway.dw.mascota;

import com.dogeway.dw.usuario.UserResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;

public record RegisterPetDTO(
        @NotBlank
        String nombre,
        @NotNull
        Raza raza,
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
        @NotNull
        Long id
) {
}
