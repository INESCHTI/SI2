package tn.esprit.championnat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.championnat.entities.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor,Long> {




}

