package com.dogeway.dw.match;

import com.dogeway.dw.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("match/")
public class matchController {
    @Autowired
    private matchRepository MatchRepository;

    @GetMapping("/list")
    public List<match> getMatch() {return MatchRepository.findAll();}

    @PostMapping("/NewMatch")
    public match NewMatch(@RequestBody match Match) {return MatchRepository.save(Match);}


    @GetMapping("/status")
    public ResponseEntity<Status> verifyStatus(@RequestParam Long id_pet, @RequestParam Long id_pet_match) {
        Status status = MatchRepository.findStatusById_petAndId_pet_match(id_pet, id_pet_match);

        if (status != null) {
            return ResponseEntity.ok(status);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
