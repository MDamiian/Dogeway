package com.dogeway.dw.match;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/list")
    public List<Match> getMatch() {
        return matchRepository.findAll();
    }

    @PostMapping("/NewMatch")
    public Match NewMatch(@RequestBody Match Match) {
        return matchRepository.save(Match);
    }

    @PostMapping("/aceptar")
    public ResponseEntity<Match> UpdateStatus(@RequestParam Long id_pet, @RequestParam Long id_pet_match, @RequestParam Status newStatus) {

        Match matchToUpdate = matchRepository.findByIdpetAndIdpetmatch(id_pet, id_pet_match);

        if (matchToUpdate != null) {
            matchToUpdate.setStatus(newStatus);
            matchRepository.save(matchToUpdate);
            return ResponseEntity.ok(matchToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Match> verifyStatus(@RequestParam Long id_pet, @RequestParam Long id_pet_match) {

        Match match = matchRepository.findByIdpetAndIdpetmatch(id_pet, id_pet_match);

        if (match != null) {
            return ResponseEntity.ok(match);
        } else {
            match = matchRepository.findByIdpetAndIdpetmatch(id_pet_match, id_pet);

            if (match != null) {
                return ResponseEntity.ok(match);

            } else {
                Match NotFound = new Match(-1L, -1L, Status.PENDIENTE);
                return ResponseEntity.ok(NotFound);
            }
        }
    }
}




