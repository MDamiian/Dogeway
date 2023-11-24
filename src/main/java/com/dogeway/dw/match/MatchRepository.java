package com.dogeway.dw.match;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findByIdpetAndIdpetmatch(Long id_pet, Long id_pet_match);


}
