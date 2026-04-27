package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.championnat.entities.Position;

import java.time.LocalDate;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select coalesce(avg(pos.classement), 0) from Position pos " +
            "where pos.pilote.libelleP = :libelleP and pos.course.dateCourse between :startDate and :endDate")
    Double moyenneClassementPiloteEntreDeuxDates(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate,
                                                  @Param("libelleP") String libelleP);
}

