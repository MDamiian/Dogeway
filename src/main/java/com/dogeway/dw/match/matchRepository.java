package com.dogeway.dw.match;

import org.springframework.data.jpa.repository.JpaRepository;

public interface matchRepository extends JpaRepository<match,Long> {

    match findByIdpetAndIdpetmatch(Long id_pet, Long id_pet_match);



}
