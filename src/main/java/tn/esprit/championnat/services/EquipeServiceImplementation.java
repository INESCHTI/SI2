package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.repositories.ChampionnatRepository;
import tn.esprit.championnat.repositories.CourseRepository;
import tn.esprit.championnat.repositories.EquipeRepository;

@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImplementation implements IEquipeService {
    private EquipeRepository equipeRepository;
    private CourseRepository courseRepository;
    private ChampionnatRepository championnatRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public Integer nbPointsParPilotesUneEquipeChampionnatPourUneAnne(Long idEquipe, Long idChampionnat, String annee) {
        log.info("Calcul points equipe={}, championnat={}, annee={}", idEquipe, idChampionnat, annee);

        if (idEquipe == null || idChampionnat == null || annee == null || annee.isBlank()) {
            log.warn("Parametres invalides pour nbPointsParPilotesUneEquipeChampionnatPourUneAnne.");
            return 0;
        }
        if (!equipeRepository.existsById(idEquipe) || !championnatRepository.existsById(idChampionnat)) {
            log.warn("Equipe ou championnat introuvable: equipe={}, championnat={}", idEquipe, idChampionnat);
            return 0;
        }

        Integer anneeInt;
        try {
            anneeInt = Integer.parseInt(annee);
        } catch (NumberFormatException e) {
            log.warn("Annee invalide: {}", annee);
            return 0;
        }

        Long somme = courseRepository.sommePointsPilotesEquipeParChampionnatEtAnnee(idEquipe, idChampionnat, anneeInt);
        int resultat = somme == null ? 0 : somme.intValue();
        log.info("Total points calcule: {}", resultat);
        return resultat;
    }
}
