package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.repository.EquipeRepository;

public class EquipeService implements IEquipeService {

    private  EquipeRepository equipeRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
}
