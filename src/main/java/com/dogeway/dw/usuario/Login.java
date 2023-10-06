package com.dogeway.dw.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Login(@Email String correo, @NotBlank String password) {
}
