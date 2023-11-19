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

    private Long id_user;
    private Long id_user_match;
    private boolean status_user;
    private boolean status_user_match;

}
