package com.dogeway.dw.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface matchRepository extends JpaRepository<match,Long> {

    match findmatchById_petAndId_pet_match(Long id_pet, Long id_pet_match);


}
