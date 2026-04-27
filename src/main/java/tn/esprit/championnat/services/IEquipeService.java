package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Equipe;

public interface IEquipeService {
    Equipe ajouterEquipe(Equipe equipe);

    Integer nbPointsParPilotesUneEquipeChampionnatPourUneAnne(Long idEquipe, Long idChampionnat, String annee);

}
