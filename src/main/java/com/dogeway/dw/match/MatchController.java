package com.dogeway.dw.match;


import com.dogeway.dw.mascota.MacotaRepository;
import com.dogeway.dw.mascota.Mascota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;
    private MacotaRepository macotaRepository;

    public MatchController(MacotaRepository macotaRepository) {
        this.macotaRepository = macotaRepository;
    }

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



    @GetMapping("/ListOfMatchForUser")
    public List<Mascota> ListOfMatchsForUser(@RequestParam Long id_pet, @RequestParam Status status) {
        List<Match> List1 = matchRepository.findByIdpetAndStatus(id_pet, status);
        List<Match> List2 = matchRepository.findByIdpetmatchAndStatus(id_pet, status);

// Iterar sobre la lista y cambiar los valores de las columnas
        for (Match match : List2) {
            // Guardar los valores originales
            Long tempIdPet = match.getIdpet();
            Long tempIdPetMatch = match.getIdpetmatch();

            // Intercambiar los valores
            match.setIdpet(tempIdPetMatch);
            match.setIdpetmatch(tempIdPet);
        }

        // Combine the lists if needed
        List<Match> combinedList = new ArrayList<>();
        combinedList.addAll(List1);
        combinedList.addAll(List2);

        if(!combinedList.isEmpty()){

            List<Long> MatchsUserIds = combinedList.stream()
                    .map(Match::getIdpetmatch)
                    .collect(Collectors.toList());


            List<Mascota> PetMatch  = macotaRepository.findAllById(MatchsUserIds);

            return PetMatch;

        }


        return Collections.emptyList();
    }




}
