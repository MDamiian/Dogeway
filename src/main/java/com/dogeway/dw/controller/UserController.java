package com.dogeway.dw.controller;

import com.dogeway.dw.usuario.RegisterDTO;
import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    @GetMapping("/user-information")
    public ResponseEntity<UserDetails> loadUserByUsername(@RequestParam String correo) {
        UserDetails userDetails = usuarioRepository.findByCorreo(correo);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            // Manejo de caso en el que el usuario no se encuentra
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid RegisterDTO registerDTO){
        String passwordEncoded = passwordEncoder.encode(registerDTO.password());

        Usuario usuario = usuarioRepository.save(new Usuario(registerDTO, passwordEncoded));

        return ResponseEntity.ok().build();
    }
}
