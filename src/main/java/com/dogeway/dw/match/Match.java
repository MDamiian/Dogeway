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
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmatch;
    private Long idpet;
    private Long idpetmatch;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Match(Long idpet, Long idpetmatch, Status status) {
        this.idpet = idpet;
        this.idpetmatch = idpetmatch;
        this.status = status;
    }

}


