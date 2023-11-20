package com.dogeway.dw.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface matchRepository extends JpaRepository<match,Long> {

    @Query("SELECT m FROM matchs m WHERE m.id_user = :id_user AND m.id_user_match = :id_user_match")
    Optional<match> findMatchByUserIds(Long id_user, Long id_user_match);

}
