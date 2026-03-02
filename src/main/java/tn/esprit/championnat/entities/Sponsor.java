package tn.esprit.championnat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;

    String nom;
    String pays;
    Float budgetAnnuel;
    Boolean bloquerContrat;
    Boolean archived;
    LocalDate dateCreation;

    LocalDate dateDerniereModification;



    @OneToMany(mappedBy = "sponsor")
    private List<Contrat> contrats;
}

