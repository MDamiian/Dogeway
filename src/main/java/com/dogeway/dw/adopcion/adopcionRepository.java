package com.dogeway.dw.adopcion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface adopcionRepository extends JpaRepository<adopcion,Long> {

    adopcion findByIduserAndIduserpropietario(Long id_user, Long id_user_propietario);


    List<adopcion> findByIduserpropietarioAndStatus(Long id_user_propietario, Status status);


}



