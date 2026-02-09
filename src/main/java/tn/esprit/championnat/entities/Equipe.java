package tn.esprit.championnat.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String libelle;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Pilote> pilotes;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Contrat> contrats;

    public Equipe() {}
}
