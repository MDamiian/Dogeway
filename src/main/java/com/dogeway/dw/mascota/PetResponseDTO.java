package com.dogeway.dw.mascota;

import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.Usuario;

import java.sql.Blob;


public record PetResponseDTO(
        Long id,
        String nombre,
        Raza raza,
        float peso,
        Tamano tamano,
        String descripcion,
        Personalidad personalidad,
        String foto,
        UserResponseDTO userResponseDTO
) {
}

