package tn.esprit.championnat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idContrat;

    Float montant;
    String annee;
    Boolean archived;
    @ManyToOne
    @JoinColumn(name = "equipe_id_equipe")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "sponsor_id_sponsor")
    private Sponsor sponsor;

}
