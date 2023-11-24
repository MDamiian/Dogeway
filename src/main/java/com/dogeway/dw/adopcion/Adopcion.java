package com.dogeway.dw.adopcion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "adopcion")
@Entity(name = "adopciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idadopcion;
    private Long iduser;
    private Long iduserpropietario;
    private String message;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Adopcion(Long iduser, Long iduserpropietario, String message, Status status) {
        this.iduser = iduser;
        this.iduserpropietario = iduserpropietario;
        this.message = message;
        this.status = status;
    }
}


