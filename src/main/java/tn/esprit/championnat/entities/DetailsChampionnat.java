package tn.esprit.championnat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "details_championnat")
public class DetailsChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailChamp;

    private String code;
    private String description;

    @OneToOne
    @JoinColumn(name = "id_championnat", unique = true)
    private Championnnat championnnat;

    public DetailsChampionnat() {}
}
