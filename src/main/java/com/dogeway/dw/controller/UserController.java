package com.dogeway.dw.controller;

import com.dogeway.dw.usuario.RegisterDTO;
import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    ResponseEntity<Page<UserResponseDTO>> userToList(@PageableDefault(size = 1) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(UserResponseDTO::new));
    }

    @GetMapping("/userById")
    public ResponseEntity<Usuario> loadUserById(@RequestParam Long Id) {
        Usuario usuario = usuarioRepository.getById(Id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            // Manejo de caso en el que el usuario no se encuentra
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Usuario> loadUserByUsername(@RequestParam String correo) {
        Usuario usuario = usuarioRepository.findUsuarioByCorreo(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            // Manejo de caso en el que el usuario no se encuentra
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid RegisterDTO registerDTO) {
        String passwordEncoded = passwordEncoder.encode(registerDTO.password());

        if (passwordEncoded != null) {
            Usuario usuario = usuarioRepository.getReferenceByCorreo(registerDTO.correo());

            if (usuario != null) {
                usuario.actualizarDatos(registerDTO, passwordEncoded);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}



