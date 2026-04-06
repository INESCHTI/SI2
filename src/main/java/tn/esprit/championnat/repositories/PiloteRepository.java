package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.championnat.entities.Categorie;
import tn.esprit.championnat.entities.Pilote;

import java.util.List;


@Repository

public interface PiloteRepository extends JpaRepository<Pilote, Long> {
    Pilote findByLibelleP(String libelleP);
    List<Pilote> findByCategorieOrderByNbPointsTotalDesc(Categorie categorie);


}
