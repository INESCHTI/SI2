package tn.esprit.championnat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pilote")
public class Pilote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;

    @Column(nullable = false)
    private String libelleP;

    @Column(nullable = false)
    private Integer nbPointsTotal;

    @Column(nullable = false)
    private Integer classementGeneral;

    public Pilote() {
    }

    public Pilote(String libelleP, Integer nbPointsTotal, Integer classementGeneral) {
        this.libelleP = libelleP;
        this.nbPointsTotal = nbPointsTotal;
        this.classementGeneral = classementGeneral;
    }

    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public String getLibelleP() {
        return libelleP;
    }

    public void setLibelleP(String libelleP) {
        this.libelleP = libelleP;
    }

    public Integer getNbPointsTotal() {
        return nbPointsTotal;
    }

    public void setNbPointsTotal(Integer nbPointsTotal) {
        this.nbPointsTotal = nbPointsTotal;
    }

    public Integer getClassementGeneral() {
        return classementGeneral;
    }

    public void setClassementGeneral(Integer classementGeneral) {
        this.classementGeneral = classementGeneral;
    }
}
