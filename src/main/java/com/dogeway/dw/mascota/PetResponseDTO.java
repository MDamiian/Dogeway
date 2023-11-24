package com.dogeway.dw.mascota;

import com.dogeway.dw.usuario.UserResponseDTO;

public record PetResponseDTO(
        Long id,
        String nombre,
        Animal animal,
        UtilidadDeMascota utilidadDeMascota,
        Tamano tamano,
        String descripcion,
        Personalidad personalidad,
        String foto,
        boolean genero,
        UserResponseDTO userResponseDTO
) {
}

