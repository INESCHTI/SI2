package tn.esprit.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.championnat.entities.Pilote;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {
}
