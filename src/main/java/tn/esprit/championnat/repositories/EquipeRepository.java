package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.championnat.entities.Equipe;

@Repository

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

}
