package com.dogeway.dw.controller;

//import com.dogeway.dw.service.EmailAunthentication;

import com.dogeway.dw.service.EmailAuthentication;
import com.dogeway.dw.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.Date;

@RestController
@RequestMapping("/signup")
public class UserRController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailAuthentication emailAuthentication;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registrarUsuario(
            @RequestParam("nombres") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("correo") String correo,
            @RequestParam("intereses") Intereses intereses,
            @RequestParam("genero") Genero genero,
            @RequestParam("fechaNacimiento") Date fechaNacimiento,
            @RequestParam("pais") String pais,
            @RequestParam("estado") String estado,
            @RequestParam("ciudad") String ciudad,
            @RequestParam("telefono") String telefono,
            @RequestParam("password") String password,
            @RequestParam("foto") MultipartFile foto,
            UriComponentsBuilder uriComponentsBuilder) {


        try {
            String passwordEncoded = passwordEncoder.encode(password);
            Usuario usuario = new Usuario(nombre, apellidos, correo, intereses, genero, fechaNacimiento, pais, estado, ciudad, telefono, passwordEncoded);

            //          Guardar la foto si se proporciona
            if (foto != null && !foto.isEmpty()) {
                String ruta = "            String ruta = \"C://Users//angel//OneDrive - Universidad de Guadalajara//ING INFORMATICA//4TO//INGENERIA DE SOFTWARE//Proyecto_final//Dogeway_Fronted//Dogeway_Front//Img_user";
                String rutaVisual="/Img_user";
                try {
                    byte[] bytes = foto.getBytes();
                    Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
                    String rutaAbsolutaVisual=rutaVisual+"/"+foto.getOriginalFilename();
                    Files.write(rutaAbsoluta, bytes);
                    usuario.setFoto(rutaAbsolutaVisual);
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

    @PostMapping("/verifier")
    public ResponseEntity<?> enviarCorreoDeVerificacion(@RequestParam String correo){
        SecureRandom random = new SecureRandom();
        String codigoVerificacion = new BigInteger(30, random).toString(6);

        emailAuthentication.enviarCorreo(correo, "Codigo de verificacion", codigoVerificacion);

        return ResponseEntity.ok(codigoVerificacion);
    }

}
