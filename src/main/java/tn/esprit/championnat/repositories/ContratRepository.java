package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.championnat.entities.Contrat;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

    List<Contrat> findByArchivedFalse();

    @Query("select c from Contrat c where c.archived = false and c.equipe is not null")
    List<Contrat> findActiveContrats();

    @Query("select coalesce(sum(c.montant),0) from Contrat c " +
            "where c.sponsor.idSponsor = :idSponsor and c.annee = :annee")

     Float sommeMontantsContratsSponsorParAnnee(@Param("idSponsor") Long idSponsor,
                                                      @Param("annee") String annee);

    @Query("select c.annee, coalesce(sum(c.montant), 0) " +
            "from Contrat c where c.equipe.libelle = :libelleEquipe " +
            "group by c.annee order by c.annee")
    List<Object[]> historiqueMontantsParAnnee(@Param("libelleEquipe") String libelleEquipe);
}