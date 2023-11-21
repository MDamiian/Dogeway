package com.dogeway.dw.mascota;

import com.dogeway.dw.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "mascotas")
@Entity(name = "Mascota")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Animal animal;
    @Enumerated(EnumType.STRING)
    private Tamano tamano;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private Personalidad personalidad;
    private String foto;
    private boolean genero;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonBackReference
    private Usuario propietario;

    public Mascota(RegisterPetDTO registerPetDTO, Usuario usuario){
        this.nombre = registerPetDTO.nombre();
        this.animal = registerPetDTO.animal();
        this.tamano = registerPetDTO.tamano();
        this.descripcion = registerPetDTO.descripcion();
        this.personalidad = registerPetDTO.personalidad();
        this.foto = registerPetDTO.foto();
        this.genero = registerPetDTO.genero();
        this.propietario = usuario;
    }
}
