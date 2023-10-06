package com.dogeway.dw.controller;

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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/signup")
public class RegistroUsuario {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<RespuestaUsuario> registrarUsuario(@RequestBody @Valid com.dogeway.dw.usuario.RegistroUsuario registroUsuario,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.save(new Usuario(registroUsuario));

        RespuestaUsuario respuestaUsuario = new RespuestaUsuario(usuario.getId(), usuario.getNombres(), usuario.getApellidos());
        URI url = uriComponentsBuilder.path("/signup/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaUsuario);
    }
}
