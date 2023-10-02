package com.dogeway.dw.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Login(String correo, String password) {
}
