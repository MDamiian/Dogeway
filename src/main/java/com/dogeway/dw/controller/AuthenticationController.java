package com.dogeway.dw.controller;

import com.dogeway.dw.security.DatosJWTToken;
import com.dogeway.dw.security.TokenService;
import com.dogeway.dw.service.EmailAuthentication;
import com.dogeway.dw.usuario.LoginDTO;
import com.dogeway.dw.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    EmailAuthentication emailAuthentication;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid LoginDTO login) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(login.correo(),
                login.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

//        emailAuthentication.enviarCorreo("osvaldo.damian72@gmail.com", "Prueba", "Hola");
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
