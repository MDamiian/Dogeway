package com.dogeway.dw.usuario;

import com.dogeway.dw.mascota.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCorreo(String correo);


}
