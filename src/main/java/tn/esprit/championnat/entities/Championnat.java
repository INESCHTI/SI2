package tn.esprit.championnat.entities;

import tn.esprit.championnat.enums.Categorie;


import jakarta.persistence.*;

@Entity
@Table(name = "championnat")
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categorie categorie;

    @Column(nullable = false)
    private String libelleC;

    @Column(nullable = false)
    private Integer annee;

    // 🔹 Constructeur vide obligatoire pour JPA
    public Championnat() {
    }

    // 🔹 Constructeur utile
    public Championnat(Categorie categorie, String libelleC, Integer annee) {
        this.categorie = categorie;
        this.libelleC = libelleC;
        this.annee = annee;
    }

    // 🔹 Getters & Setters
    public Long getIdChampionnat() {
        return idChampionnat;
    }

    public void setIdChampionnat(Long idChampionnat) {
        this.idChampionnat = idChampionnat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getLibelleC() {
        return libelleC;
    }

    public void setLibelleC(String libelleC) {
        this.libelleC = libelleC;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }
}
