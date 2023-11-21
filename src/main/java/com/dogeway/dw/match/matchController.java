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


    @PostMapping("/update_status")
        public ResponseEntity<match> UpdateStatus(@RequestParam Long id_pet, @RequestParam Long id_pet_match,@RequestParam Status newStatus){

        match matchToUpdate = MatchRepository.findmatchById_petAndId_pet_match(id_pet, id_pet_match);

        if (matchToUpdate != null) {
            matchToUpdate.setStatus(newStatus);
            MatchRepository.save(matchToUpdate);
            return ResponseEntity.ok(matchToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/status")
    public ResponseEntity<match> verifyStatus(@RequestParam Long id_pet, @RequestParam Long id_pet_match) {

            match Match = MatchRepository.findmatchById_petAndId_pet_match(id_pet, id_pet_match);

                if (Match != null) {
                    return ResponseEntity.ok(Match);

                } else {
                    Match = MatchRepository.findmatchById_petAndId_pet_match(id_pet_match,id_pet);

                    if(Match!=null){
                        return ResponseEntity.ok(Match);

                    }else{
                        return ResponseEntity.notFound().build();
                    }

                }
            }

    }




