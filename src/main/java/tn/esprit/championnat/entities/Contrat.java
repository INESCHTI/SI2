package tn.esprit.championnat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private Float montant;
    private String annee;
    private Boolean archived;

    @ManyToOne
    @JoinColumn(name = "id_sponsor")
    private Sponsor sponsor;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
    private Equipe equipe;

    public Contrat() {}
}
