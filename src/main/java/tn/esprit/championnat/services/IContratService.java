package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Contrat;

public interface IContratService {

    Contrat ajouterContratEtAffecterASponsorEtEquipe(Contrat c, String libEq, String nomSponsor, String pays);

}