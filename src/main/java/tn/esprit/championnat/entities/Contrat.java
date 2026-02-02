package tn.esprit.championnat.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    private Float montant;

    @Column(nullable = false)
    private String annee;

    @Column(nullable = false)
    private Boolean archived;

    public Contrat() {
    }

    public Contrat(Float montant, String annee, Boolean archived) {
        this.montant = montant;
        this.annee = annee;
        this.archived = archived;
    }

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
}
