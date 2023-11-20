package com.dogeway.dw.mascota;

import com.dogeway.dw.usuario.Usuario;
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
    private float peso;
    @Enumerated(EnumType.STRING)
    private Tamano tamano;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private Personalidad personalidad;
    private String foto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Usuario propietario;

    public Mascota(RegisterPetDTO registerPetDTO, Usuario usuario){
        this.nombre = registerPetDTO.nombre();
        this.animal = registerPetDTO.animal();
        this.peso = registerPetDTO.peso();
        this.tamano = registerPetDTO.tamano();
        this.descripcion = registerPetDTO.descripcion();
        this.personalidad = registerPetDTO.personalidad();
        this.foto = registerPetDTO.foto();
        this.propietario = usuario;
    }
}