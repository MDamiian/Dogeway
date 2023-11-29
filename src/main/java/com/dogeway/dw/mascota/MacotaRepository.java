package com.dogeway.dw.mascota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MacotaRepository extends JpaRepository<Mascota, Long> {

    Page<Mascota> findByAnimalAndTamanoAndGeneroAndUtilidadDeMascota(Animal animal, Tamano tamano, boolean genero, Pageable paginacion, UtilidadDeMascota utilidadDeMascota);

    List<Mascota> findAllByPropietarioCorreoAndUtilidadDeMascota(String correo, UtilidadDeMascota utilidadDeMascota);

    List<Mascota> findAllByPropietarioCorreo(String correo);
    Page<Mascota> findByUtilidadDeMascota(Pageable paginacion, UtilidadDeMascota utilidadDeMascota);

    Mascota getReferenceByPropietarioIdAndNombre(Long id, String nombre);
}
