package com.dogeway.dw.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findByIdpetAndIdpetmatch(Long id_pet, Long id_pet_match);

    List<Match> findByIdpetAndStatus(Long id_pet,Status status);


    List<Match> findByIdpetmatchAndStatus(Long id_pet,Status status);






}
