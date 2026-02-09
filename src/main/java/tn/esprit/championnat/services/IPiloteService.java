package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.entities.Pilote;

import java.util.List;

public interface IPiloteService {
    Pilote ajouterPilote(Pilote Pilote);

    List<Pilote> ajouterPilotes(List<Pilote> Pilotes);

    Pilote modifierPilote(Pilote Pilote);

    void supprimerPilote (Long idPilote);

    List<Pilote> listPilotes();

    Pilote recupererPilote(Long idPilote);

    Boolean archiverPilote(Long idPilote);

}
