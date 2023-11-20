package com.dogeway.dw.mascota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MacotaRepository extends JpaRepository<Mascota, Long> {
    Page<Mascota> findByRaza(@Param("raza") Raza raza, Pageable pageable);

}
