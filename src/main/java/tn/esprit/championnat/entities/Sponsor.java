package tn.esprit.championnat.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sponsor")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;

    private String nom;
    private String pays;
    private Float budgetAnnuel;
    private Boolean bloquerContrat;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    private List<Contrat> contrats;

    public Sponsor() {}
}
