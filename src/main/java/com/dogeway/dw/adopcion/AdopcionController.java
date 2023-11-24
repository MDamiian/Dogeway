package com.dogeway.dw.adopcion;

import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/adopcion")
public class AdopcionController {
    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/list")
    public List<Adopcion> getAdpcion() {
        return adopcionRepository.findAll();
    }

    @PostMapping("/NewAdopcion")
    public Adopcion NewAdopcion(@RequestBody Adopcion Adopcion) {
        return adopcionRepository.save(Adopcion);
    }

    @PostMapping("/aceptar")
    public ResponseEntity<Adopcion> UpdateStatus(@RequestParam Long id_user, @RequestParam Long id_user_propietario, @RequestParam Status newStatus) {

        Adopcion adopcionToUpdate = adopcionRepository.findByIduserAndIduserpropietario(id_user, id_user_propietario);

        if (adopcionToUpdate != null) {
            adopcionToUpdate.setStatus(newStatus);
            adopcionRepository.save(adopcionToUpdate);
            return ResponseEntity.ok(adopcionToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/solicitudes")
    List<Usuario> getSolicitudes(@RequestParam Long id_user_propietario, @RequestParam Status status) {
        List<Adopcion> solicitudesAdopcion = adopcionRepository.findByIduserpropietarioAndStatus(id_user_propietario, status);

        // Verificar si la lista de solicitudes no está vacía antes de procesar
        if (!solicitudesAdopcion.isEmpty()) {
            // Obtener la lista de IDs de usuario desde las solicitudes
            List<Long> solicitudUserIds = solicitudesAdopcion.stream()
                    .map(Adopcion::getIduser)
                    .collect(Collectors.toList());

            // Obtener la lista de usuarios usando findAllById
            List<Usuario> solicitudesUsuarios = usuarioRepository.findAllById(solicitudUserIds);
            return solicitudesUsuarios;
        } else {
            // Manejar el caso en el que no hay solicitudes encontradas
            return Collections.emptyList();
        }
    }


    @GetMapping("/status")
    public ResponseEntity<Adopcion> verifyStatus(@RequestParam Long id_user, @RequestParam Long id_user_propietario) {

        Adopcion Adopcion = adopcionRepository.findByIduserAndIduserpropietario(id_user, id_user_propietario);

        if (Adopcion != null) {
            return ResponseEntity.ok(Adopcion);
        } else {
            Adopcion = adopcionRepository.findByIduserAndIduserpropietario(id_user_propietario, id_user);

            if (Adopcion != null) {
                return ResponseEntity.ok(Adopcion);

            } else {
                com.dogeway.dw.adopcion.Adopcion NotFound = new Adopcion(-1L, -1L, "NOTFOUND", Status.PENDIENTE);
                return ResponseEntity.ok(NotFound);
            }

        }
    }

}







