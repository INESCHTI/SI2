package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Championnat;
import tn.esprit.championnat.entities.Course;
import tn.esprit.championnat.entities.DetailChampionnat;
import tn.esprit.championnat.repositories.ChampionnatRepository;
import tn.esprit.championnat.repositories.CourseRepository;

    @Service
    @AllArgsConstructor
    public class ChampionnatServiceImplementation implements IChampionnatService {

        private ChampionnatRepository championnatRepository;
        private CourseRepository courseRepository;

        @Override
        public Championnat addChampAndAssociatedCourses(Championnat c) {
            return championnatRepository.save(c);
        }

        @Override
        public DetailChampionnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat dt, Long idC) {
            Championnat champ = championnatRepository.findById(idC).orElse(null);
            if (champ == null) return null;

            champ.setDetailChampionnat(dt);
            championnatRepository.save(champ);

            return dt;
        }

        @Override
        public String affecterCourseAChampionnat(Long idCourse, Long idChampionnat) {

            Course course = courseRepository.findById(idCourse).orElse(null);
            Championnat champ = championnatRepository.findById(idChampionnat).orElse(null);

            if (course == null || champ == null) return "NOT_FOUND";

            champ.getCourses().add(course);
            course.getChampionnats().add(champ);

            championnatRepository.save(champ);

            return "AFFECTED";
        }
    }

