package com.dogeway.dw.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/match")
public class matchController {
    @Autowired
    private matchRepository MatchRepository;

    @GetMapping("/list")
    public List<match> getMatch() {return MatchRepository.findAll();}

    @PostMapping("/NewMatch")
    public match NewMatch(@RequestBody match Match) {return MatchRepository.save(Match);}

}
