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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/signup")
public class UserRController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
//    EmailAunthentication emailAunthentication;


    @PostMapping
    public ResponseEntity<UserResponseDTO> registrarUsuario(
            @RequestBody @Valid RegisterDTO registroUsuario,
            UriComponentsBuilder uriComponentsBuilder) {

        try {
            String passwordEncoded = passwordEncoder.encode(registroUsuario.password());
//            emailAunthentication.emailAuthentication(registroUsuario.correo());

            Usuario usuario = usuarioRepository.save(new Usuario(registroUsuario, passwordEncoded));

            UserResponseDTO respuestaUsuario = new UserResponseDTO(usuario.getId(), usuario.getNombres(), usuario.getApellidos());
            URI url = uriComponentsBuilder.path("/signup/{id}").buildAndExpand(usuario.getId()).toUri();

            return ResponseEntity.created(url).body(respuestaUsuario);
        } catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
