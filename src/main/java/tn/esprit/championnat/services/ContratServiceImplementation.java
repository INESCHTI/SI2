package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Contrat;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.entities.Sponsor;
import tn.esprit.championnat.repositories.ContratRepository;
import tn.esprit.championnat.repositories.EquipeRepository;
import tn.esprit.championnat.repositories.SponsorRepository;

@Service
@AllArgsConstructor
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
}