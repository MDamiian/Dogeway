package com.dogeway.dw.adopcion;

import com.dogeway.dw.match.match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adopcionRepository extends JpaRepository<adopcion,Long> {

    adopcion findByIduserAndIduserpropietario(Long id_user, Long id_user_propietario);



}
