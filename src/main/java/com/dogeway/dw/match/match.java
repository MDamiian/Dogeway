package com.dogeway.dw.match;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "matchs")
@Entity(name = "matchs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class match{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_match;

    private Long id_pet;
    private Long id_pet_match;

    @Enumerated(EnumType.STRING)
    private Status status;

}
