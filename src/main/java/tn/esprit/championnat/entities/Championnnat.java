package tn.esprit.championnat.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "championnat")
public class Championnnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelleC;
    private Integer annee;

    @ManyToMany
    @JoinTable(
            name = "championnat_course",
            joinColumns = @JoinColumn(name = "id_championnat"),
            inverseJoinColumns = @JoinColumn(name = "id_course")
    )
    private List<Course> courses;

    @OneToOne(mappedBy = "championnnat", cascade = CascadeType.ALL)
    private DetailsChampionnat detailsChampionnat;

    public Championnnat() {}
}
