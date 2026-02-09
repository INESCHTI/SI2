package tn.esprit.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.championnat.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe,Long> {
}
