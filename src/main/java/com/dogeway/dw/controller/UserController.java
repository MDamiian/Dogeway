package com.dogeway.dw.controller;

import com.dogeway.dw.service.EmailAuthentication;
import com.dogeway.dw.usuario.*;
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

import java.math.BigInteger;
import java.security.SecureRandom;


@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailAuthentication emailAuthentication;

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

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<UserUpdateDto> updateUser(@RequestBody @Valid UserUpdateDto registerDTO) {



        if (registerDTO.password() == null) {

            Usuario usuario = usuarioRepository.getReferenceByCorreo(registerDTO.correo());

            if (usuario != null) {
                usuario.actualizarDatos(registerDTO, usuario.getPassword());
                return ResponseEntity.ok(registerDTO);

            }

        }else{
            String passwordEncoded = passwordEncoder.encode(registerDTO.password());
            Usuario usuario = usuarioRepository.getReferenceByCorreo(registerDTO.correo());

            if (usuario != null) {
                usuario.actualizarDatos(registerDTO, passwordEncoded);

                return ResponseEntity.ok().build();

            }


        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

   /*DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();


        En la clase usuario agregar, y un atributo a la tabla boolean "activo":

        public void desactivarUsuario() {
        this.activo = false;
    }
    }*/


    //DELETE fisico
    @DeleteMapping("/{id}") //Para decir que va a tener una variable
    @Transactional
    public void eliminarUsuario(@PathVariable Long id){ //Para decir que viene del path
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }


}



