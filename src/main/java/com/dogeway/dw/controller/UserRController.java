package com.dogeway.dw.controller;

//import com.dogeway.dw.service.EmailAunthentication;

import com.dogeway.dw.usuario.RegisterDTO;
import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/signup")
public class UserRController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
//    EmailAunthentication emailAunthentication;


    /*@PostMapping
    public ResponseEntity<UserResponseDTO> registrarUsuario(
            @RequestBody @Valid RegisterDTO registroUsuario,
            UriComponentsBuilder uriComponentsBuilder) {

        try {
            String passwordEncoded = passwordEncoder.encode(registroUsuario.password());
//            emailAunthentication.emailAuthentication(registroUsuario.correo());

            Usuario usuario = usuarioRepository.save(new Usuario(registroUsuario, passwordEncoded));

            UserResponseDTO respuestaUsuario = new UserResponseDTO(usuario.getId(), usuario.getNombres(),
                    usuario.getApellidos(), usuario.getEstado(), usuario.getCiudad(), usuario.getGenero(),
                    usuario.getFecha_nacimiento(), usuario.getFoto());

            URI url = uriComponentsBuilder.path("/signup/{id}").buildAndExpand(usuario.getId()).toUri();

            return ResponseEntity.created(url).body(respuestaUsuario);
        } catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }*/

    @PostMapping("/registrarUsuario")
    public ResponseEntity<UserResponseDTO> registrarUsuario(
            @RequestPart(name = "file", required = false) MultipartFile foto,
            @RequestPart("registerDTO") @Valid RegisterDTO registroUsuario,
            UriComponentsBuilder uriComponentsBuilder) {

        try {
            String passwordEncoded = passwordEncoder.encode(registroUsuario.password());

            Usuario usuario = new Usuario(registroUsuario, passwordEncoded);

            // Guardar la foto si se proporciona
            if (foto != null && !foto.isEmpty()) {
                String ruta = "C://Users//angel//OneDrive - Universidad de Guadalajara//ING INFORMATICA//4TO//INGENERIA DE SOFTWARE//fotos";
                try {
                    byte[] bytes = foto.getBytes();
                    Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
                    Files.write(rutaAbsoluta, bytes);
                    usuario.setFoto(foto.getOriginalFilename());
                } catch (Exception e) {
                    // Manejar la excepción según tus necesidades
                }
            }

            usuarioRepository.save(usuario);

            UserResponseDTO respuestaUsuario = new UserResponseDTO(usuario.getId(), usuario.getNombres(),
                    usuario.getApellidos(), usuario.getEstado(), usuario.getCiudad(), usuario.getGenero(),
                    usuario.getFecha_nacimiento(), usuario.getFoto());

            URI url = uriComponentsBuilder.path("/signup/{id}").buildAndExpand(usuario.getId()).toUri();

            return ResponseEntity.created(url).body(respuestaUsuario);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
