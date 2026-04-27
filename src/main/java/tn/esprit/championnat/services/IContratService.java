package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Contrat;

import java.util.HashMap;

public interface IContratService {

    Contrat ajouterContratEtAffecterASponsorEtEquipe(Contrat c, String libEq, String nomSponsor, String pays);

    HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe);
}