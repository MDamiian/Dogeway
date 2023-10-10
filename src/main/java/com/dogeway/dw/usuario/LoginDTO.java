package com.dogeway.dw.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DTOlogin(@Email String correo, @NotBlank String password) {
}
