package com.dogeway.dw.adopcion;


import com.dogeway.dw.match.Status;
import com.dogeway.dw.match.match;
import com.dogeway.dw.match.matchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/adopcion")
public class adopcionController {
    @Autowired
    private adopcionRepository AdopcionRepository;

    @GetMapping("/list")
    public List<adopcion> getAdpcion() {return AdopcionRepository.findAll();}

    @PostMapping("/NewAdopcion")
    public adopcion NewAdopcion(@RequestBody adopcion Adopcion) {return AdopcionRepository.save(Adopcion);}


    @PostMapping("/aceptar")
        public ResponseEntity<adopcion> UpdateStatus(@RequestParam Long id_user, @RequestParam Long id_user_propietario,@RequestParam String message, @RequestParam Status newStatus){

        adopcion adopcionToUpdate = AdopcionRepository.findByIduserAndIduserpropietario(id_user,id_user_propietario);

        if (adopcionToUpdate != null) {
            adopcionToUpdate.setStatus(newStatus);
            AdopcionRepository.save(adopcionToUpdate);
            return ResponseEntity.ok(adopcionToUpdate);
        } else {
            return ResponseEntity.notFound().build();
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




