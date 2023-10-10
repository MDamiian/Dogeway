package com.dogeway.dw.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroUsuario(
        @NotBlank
        String nombres,
        @NotBlank
        String apellidos,
        @NotBlank
        @Email
        String correo,
        String telefono,
        @NotBlank
        String password
) {
}
