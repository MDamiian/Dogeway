package com.dogeway.dw.mascota;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Mascota")
@Table(name = "mascotas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String raza;
    private String peso;
    @Enumerated(EnumType.STRING)
    private Tamano tamano;
}
