package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.championnat.entities.Contrat;

public interface ContratRepository extends JpaRepository<Contrat, Long> {}