package com.dogeway.dw.adopcion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {

    Adopcion findByIduserAndIduserpropietario(Long id_user, Long id_user_propietario);


    List<Adopcion> findByIduserpropietarioAndStatus(Long id_user_propietario, Status status);


}



