package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.repositories.EquipeRepository;
import tn.esprit.championnat.repositories.PiloteRepository;
import tn.esprit.championnat.repositories.PositionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PiloteServiceImplementation implements IPiloteService {
    private PiloteRepository piloteRepository;
    private EquipeRepository equipeRepository;
    private PositionRepository positionRepository;

    @Override
    public String addPilote(Pilote p) {
        piloteRepository.save(p);
        return "Pilote ajoute avec succes";
    }

    public List<Pilote> addPilotes(List<Pilote> pilotes) {
        return piloteRepository.saveAll(pilotes);
    }

    @Override
    public void deleteAllPilotes() {
        piloteRepository.deleteAll();
    }

    @Override
    public Pilote affecterPiloteAEquipe(String libP, String libE) {

        Pilote p = piloteRepository.findByLibelleP(libP);
        Equipe e = equipeRepository.findByLibelle(libE);

        if (p == null || e == null) {
            return null;
        }

        p.setEquipe(e);
        return piloteRepository.save(p);
    }

    @Override
    public Float moyennePositionsEntreDeuxDate(LocalDate startDate, LocalDate endDate, String libelleP) {
        log.info("Calcul moyenne positions pilote={}, startDate={}, endDate={}", libelleP, startDate, endDate);

        if (startDate == null || endDate == null || libelleP == null || libelleP.isBlank()) {
            log.warn("Parametres invalides pour moyennePositionsEntreDeuxDate.");
            return 0f;
        }
        if (startDate.isAfter(endDate)) {
            log.warn("Intervalle invalide: startDate > endDate.");
            return 0f;
        }

        Double moyenne = positionRepository.moyenneClassementPiloteEntreDeuxDates(startDate, endDate, libelleP);
        float resultat = moyenne == null ? 0f : moyenne.floatValue();
        log.info("Moyenne des positions calculee pour {} = {}", libelleP, resultat);
        return resultat;
    }
}
