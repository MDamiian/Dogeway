package com.dogeway.dw.usuario;

public record UserResponseDTO(Long id, String nombres, String apellidos) {
    public UserResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombres(), usuario.getApellidos());
    }
}
