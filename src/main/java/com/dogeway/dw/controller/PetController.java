package com.dogeway.dw.controller;

import com.dogeway.dw.mascota.*;
import com.dogeway.dw.usuario.UserResponseDTO;
import com.dogeway.dw.usuario.Usuario;
import com.dogeway.dw.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private MacotaRepository mascotaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/explore")
    public ResponseEntity<Page<PetResponseDTO>> mascotaToList(@PageableDefault(size = 1) Pageable paginacion) {
        Page<Mascota> paginaMascotas = mascotaRepository.findAll(paginacion);

        List<PetResponseDTO> listaMascotasDTO = paginaMascotas.map
                (
                        mascota -> new PetResponseDTO
                                (
                                        mascota.getIdMascota(), mascota.getNombre(),
                                        mascota.getAnimal(), mascota.getUtilidadDeMascota(), mascota.getTamano(),
                                        mascota.getDescripcion(), mascota.getPersonalidad(),
                                        mascota.getFoto(), mascota.isGenero(), new UserResponseDTO(mascota.getPropietario())
                                )
                ).getContent();

        Page<PetResponseDTO> paginaMascotasDTO = new PageImpl<>(listaMascotasDTO,
                paginacion, paginaMascotas.getTotalElements());

        return ResponseEntity.ok(paginaMascotasDTO);
    }

    @GetMapping("/explore-byanimal")
    public ResponseEntity<Page<PetResponseDTO>> mascotaToListByAnimal(@PageableDefault(size = 1) Pageable paginacion,
                                                                      @RequestParam Animal animal, @RequestParam Tamano tamano, @RequestParam boolean genero, @RequestParam UtilidadDeMascota utilidadDeMascota) {

        Page<Mascota> paginaMascotas = mascotaRepository.findByAnimalAndTamanoAndGeneroAndUtilidadDeMascota(animal, tamano, genero, paginacion, utilidadDeMascota);

        List<PetResponseDTO> listaMascotasDTO = paginaMascotas.map
                (
                        mascota -> new PetResponseDTO
                                (
                                        mascota.getIdMascota(), mascota.getNombre(),
                                        mascota.getAnimal(), mascota.getUtilidadDeMascota(), mascota.getTamano(),
                                        mascota.getDescripcion(), mascota.getPersonalidad(),
                                        mascota.getFoto(), mascota.isGenero(), new UserResponseDTO(mascota.getPropietario())
                                )
                ).getContent();
        Page<PetResponseDTO> paginaMascotasDTO = new PageImpl<>(listaMascotasDTO,
                paginacion, paginaMascotas.getTotalElements());

        return ResponseEntity.ok(paginaMascotasDTO);
    }

    @GetMapping("/exploreAdopt")
    public ResponseEntity<Page<PetResponseDTO>> AdoptToListByAnimal(@PageableDefault(size = 1) Pageable paginacion,
                                                                    @RequestParam UtilidadDeMascota utilidadDeMascota) {

        Page<Mascota> paginaMascotas = mascotaRepository.findByUtilidadDeMascota(paginacion, utilidadDeMascota);

        List<PetResponseDTO> listaMascotasDTO = paginaMascotas.map
                (
                        mascota -> new PetResponseDTO
                                (
                                        mascota.getIdMascota(), mascota.getNombre(),
                                        mascota.getAnimal(), mascota.getUtilidadDeMascota(), mascota.getTamano(),
                                        mascota.getDescripcion(), mascota.getPersonalidad(),
                                        mascota.getFoto(), mascota.isGenero(), new UserResponseDTO(mascota.getPropietario())
                                )
                ).getContent();

        Page<PetResponseDTO> paginaMascotasDTO = new PageImpl<>(listaMascotasDTO,
                paginacion, paginaMascotas.getTotalElements());

        return ResponseEntity.ok(paginaMascotasDTO);
    }


    @GetMapping("/listallpets")
    public ResponseEntity<List<PetResponseDTO>> listAllPets(@RequestParam String correo,
                                                            @RequestParam UtilidadDeMascota utilidadDeMascota) {

        List<Mascota> mascotas = mascotaRepository.findAllByPropietarioCorreoAndUtilidadDeMascota
                (correo, utilidadDeMascota);

        List<PetResponseDTO> petResponseDTOs = mascotas.stream()
                .map(mascota -> new PetResponseDTO(mascota.getIdMascota(), mascota.getNombre(),
                        mascota.getAnimal(), mascota.getUtilidadDeMascota(), mascota.getTamano(),
                        mascota.getDescripcion(), mascota.getPersonalidad(),
                        mascota.getFoto(), mascota.isGenero(), new UserResponseDTO(mascota.getPropietario())))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(petResponseDTOs);
    }


    @PostMapping("/create")
    public ResponseEntity<PetResponseDTO> registrarMascota(@RequestBody @Valid RegisterPetDTO registroMascota, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Usuario> propietarioOptional = usuarioRepository.findById(registroMascota.id());

        Mascota mascota = mascotaRepository.save(new Mascota(registroMascota, propietarioOptional.get()));

        PetResponseDTO petResponseDTO = new PetResponseDTO
                (
                        mascota.getIdMascota(), mascota.getNombre(),
                        mascota.getAnimal(), mascota.getUtilidadDeMascota(), mascota.getTamano(),
                        mascota.getDescripcion(), mascota.getPersonalidad(),
                        mascota.getFoto(), mascota.isGenero(), new UserResponseDTO(mascota.getPropietario())
                );

        URI url = uriComponentsBuilder.path("/create/{id}").buildAndExpand(mascota.getIdMascota()).toUri();

        return ResponseEntity.created(url).body(petResponseDTO);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<UserResponseDTO> updatePet(@RequestBody @Valid RegisterPetDTO registerPetDTO) {
        Mascota mascota = new Mascota();
        mascota.actualizarDatos(registerPetDTO);
        return ResponseEntity.ok().build();
    }
}
