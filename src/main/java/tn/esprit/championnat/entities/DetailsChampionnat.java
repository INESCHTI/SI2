package tn.esprit.championnat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "details_championnat")
public class DetailsChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailChamp;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    public DetailsChampionnat() {
    }

    public DetailsChampionnat(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getIdDetailChamp() {
        return idDetailChamp;
    }

    public void setIdDetailChamp(Long idDetailChamp) {
        this.idDetailChamp = idDetailChamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
