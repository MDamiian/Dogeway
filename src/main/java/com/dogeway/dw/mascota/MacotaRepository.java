package com.dogeway.dw.mascota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacotaRepository extends JpaRepository<Mascota, Long> {

    Page<Mascota> findByAnimal(Animal animal, Pageable paginacion);
}
