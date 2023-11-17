package com.dogeway.dw.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

public record RegisterDTO(
        @NotBlank
        String nombres,
        @NotBlank
        String apellidos,
        @NotBlank
        @Email
        String correo,
        @NotNull
        @Enumerated(EnumType.STRING)
        Intereses intereses,
        @NotNull
        @Enumerated(EnumType.STRING)
        Genero genero,
        @Past
        Date fechaNacimiento,
        @NotBlank
        String pais,
        @NotBlank
        String estado,
        @NotBlank
        String ciudad,
        @NotBlank
        String telefono,
        @NotNull
        String foto,
        @NotBlank
        String password
) {
}
