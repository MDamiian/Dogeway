package com.dogeway.dw.mascota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MacotaRepository extends JpaRepository<Mascota, Long> {

    Page<Mascota> findByAnimalAndTamanoAndGenero(Animal animal, Tamano tamano, boolean genero, Pageable paginacion);
    List<Mascota> findAllByPropietarioCorreo(String correo);
}
