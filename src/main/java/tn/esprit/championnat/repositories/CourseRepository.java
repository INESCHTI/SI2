package tn.esprit.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.championnat.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select coalesce(sum(pos.nbPoints), 0) " +
            "from Position pos join pos.course c join c.championnats ch " +
            "where pos.pilote.equipe.idEquipe = :idEquipe " +
            "and ch.idChampionnat = :idChampionnat and ch.annee = :annee")
    Long sommePointsPilotesEquipeParChampionnatEtAnnee(@Param("idEquipe") Long idEquipe,
                                                        @Param("idChampionnat") Long idChampionnat,
                                                        @Param("annee") Integer annee);
}
