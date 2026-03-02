package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.championnat.entities.Sponsor;

@Repository

public interface SponsorRepository extends JpaRepository<Sponsor,Long > {

}
