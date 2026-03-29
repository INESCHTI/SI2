package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Championnat;
import tn.esprit.championnat.entities.DetailChampionnat;

public interface IChampionnatService {

    Championnat addChampAndAssociatedCourses(Championnat c);

    DetailChampionnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat dt, Long idC);

    String affecterCourseAChampionnat(Long idCourse, Long idChampionnat);

}