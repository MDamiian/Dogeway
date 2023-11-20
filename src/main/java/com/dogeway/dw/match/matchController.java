package com.dogeway.dw.match;

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


    //Retorna un usuario en la tabla de match
    @GetMapping("/{id}")
    public ResponseEntity<match> getMatchById(@PathVariable Long id) {
        Optional<match> matchOptional = MatchRepository.findById(id);

        return matchOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Verifica si el status de ambos usuarios es true y significa que ya hicieron match
    @GetMapping("/checkMatch")
    public boolean checkMatch(@RequestParam Long id_user, @RequestParam Long id_user_match) {
        // Buscar el match por IDs de usuario
        Optional<match> optionalMatch = MatchRepository.findMatchByUserIds(id_user, id_user_match);

        // Verificar si se encontró el match y cumplen las condiciones
        return optionalMatch.map(match -> match.isStatus_user() && match.isStatus_user_match()).orElse(false);
    }


}
