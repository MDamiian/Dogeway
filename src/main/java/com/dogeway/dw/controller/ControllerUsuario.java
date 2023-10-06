package com.dogeway.dw.controller;

import com.auth0.jwt.algorithms.Algorithm;
import com.dogeway.dw.usuario.RegistroUsuario;
import com.dogeway.dw.usuario.RespuestaUsuario;
import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {
}
