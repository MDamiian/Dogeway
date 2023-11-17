package com.dogeway.dw.controller;

import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    ResponseEntity<Page<UserResponseDTO>> userToList(@PageableDefault(size = 1) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(UserResponseDTO::new));
    }
}
