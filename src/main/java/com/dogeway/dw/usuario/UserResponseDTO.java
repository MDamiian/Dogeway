package com.dogeway.dw.usuario;

import java.sql.Date;

public record UserResponseDTO(Long id, String nombres, String apellidos, String estado, String ciudad, Genero genero, Date Fecha_nacimiento) {
    public UserResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombres(), usuario.getApellidos(), usuario.getEstado(), usuario.getCiudad(), usuario.getGenero(), usuario.getFecha_nacimiento());
    }
}
