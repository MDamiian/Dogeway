package com.dogeway.dw.match;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface matchRepository extends JpaRepository<match,Long> {

    match findByIdPetAndIdPetMatch(Long id_pet, Long id_pet_match);



}
