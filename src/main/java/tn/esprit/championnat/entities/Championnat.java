package tn.esprit.championnat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Championnat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idChampionnat;

    @Enumerated(EnumType.STRING)
    Categorie categorie;

    String libelleC;
    Integer annee;

    @OneToOne(cascade = CascadeType.ALL)
    private DetailChampionnat detailChampionnat;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courses;




    @Override
    public String toString() {
        return "Championnat{" +
                "idChampionnat=" + idChampionnat +
                ", categorie=" + categorie +
                ", libelleC='" + libelleC + '\'' +
                ", annee=" + annee +
                '}';
    }



}

