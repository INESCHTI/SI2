package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.championnat.entities.Championnat;

public interface ChampionnatRepository extends JpaRepository<Championnat, Long> {}