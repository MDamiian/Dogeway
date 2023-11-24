package com.dogeway.dw.adopcion;

import com.dogeway.dw.adopcion.Status;
import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/adopcion")
public class adopcionController {
    @Autowired
    private adopcionRepository AdopcionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/list")
    public List<adopcion> getAdpcion() {return AdopcionRepository.findAll();}

    @PostMapping("/NewAdopcion")
    public adopcion NewAdopcion(@RequestBody adopcion Adopcion) {return AdopcionRepository.save(Adopcion);}


    @PostMapping("/aceptar")
        public ResponseEntity<adopcion> UpdateStatus(@RequestParam Long id_user, @RequestParam Long id_user_propietario,@RequestParam Status newStatus){

        adopcion adopcionToUpdate = AdopcionRepository.findByIduserAndIduserpropietario(id_user,id_user_propietario);

        if (adopcionToUpdate != null) {
            adopcionToUpdate.setStatus(newStatus);
            AdopcionRepository.save(adopcionToUpdate);
            return ResponseEntity.ok(adopcionToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/solicitudes")
    List<Usuario> getSolicitudes(@RequestParam Long id_user_propietario, @RequestParam Status status) {
        List<adopcion> solicitudesAdopcion = AdopcionRepository.findByIduserpropietarioAndStatus(id_user_propietario, status);

        // Verificar si la lista de solicitudes no está vacía antes de procesar
        if (!solicitudesAdopcion.isEmpty()) {
            // Obtener la lista de IDs de usuario desde las solicitudes
            List<Long> solicitudUserIds = solicitudesAdopcion.stream()
                    .map(adopcion::getIduser)
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
    public ResponseEntity<adopcion> verifyStatus(@RequestParam Long id_user, @RequestParam Long id_user_propietario) {

            adopcion Adopcion = AdopcionRepository.findByIduserAndIduserpropietario(id_user, id_user_propietario);

                if (Adopcion != null) {
                    return ResponseEntity.ok(Adopcion);
                } else {
                    Adopcion = AdopcionRepository.findByIduserAndIduserpropietario(id_user_propietario,id_user);

                    if(Adopcion!=null){
                        return ResponseEntity.ok(Adopcion);

                    }else{
                        adopcion NotFound=new adopcion(-1L,-1L,"NOTFOUND",Status.PENDIENTE);
                        return ResponseEntity.ok(NotFound);
                    }

                }
            }

    }







