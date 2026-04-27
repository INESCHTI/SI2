package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Contrat;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.entities.Sponsor;
import tn.esprit.championnat.repositories.ContratRepository;
import tn.esprit.championnat.repositories.EquipeRepository;
import tn.esprit.championnat.repositories.SponsorRepository;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ContratServiceImplementation implements IContratService {

    private ContratRepository contratRepository;
    private EquipeRepository equipeRepository;
    private SponsorRepository sponsorRepository;

    @Override
    public Contrat ajouterContratEtAffecterASponsorEtEquipe(Contrat c, String libEq, String nomSponsor, String pays) {

        Equipe e = equipeRepository.findByLibelle(libEq);
        Sponsor s = sponsorRepository.findByNomAndPays(nomSponsor, pays);

        if (e == null || s == null) return null;

        c.setEquipe(e);
        c.setSponsor(s);

        return contratRepository.save(c);
    }

    @Override
    public HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe) {
        log.info("Calcul historique contrats pour equipe={}.", libelleEquipe);

        HashMap<String, Float> historique = new HashMap<>();
        if (libelleEquipe == null || libelleEquipe.isBlank()) {
            log.warn("historiqueContratsEquipe appele avec un libelle vide.");
            return historique;
        }

        List<Object[]> lignes = contratRepository.historiqueMontantsParAnnee(libelleEquipe);
        for (Object[] ligne : lignes) {
            String annee = (String) ligne[0];
            Float montant = ((Number) ligne[1]).floatValue();
            historique.put(annee, montant);
        }

        log.info("Historique calcule pour equipe={} -> {} annees.", libelleEquipe, historique.size());
        return historique;
    }
}