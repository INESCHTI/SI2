package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Pilote;

import java.util.List;

public class PiloteService implements IPiloteService{


    @Override
    public Pilote ajouterPilote(Pilote Pilote) {
        return null;
    }

    @Override
    public List<Pilote> ajouterPilotes(List<Pilote> Pilotes) {
        return List.of();
    }

    @Override
    public Pilote modifierPilote(Pilote Pilote) {
        return null;
    }

    @Override
    public void supprimerPilote(Long idPilote) {

    }

    @Override
    public List<Pilote> listPilotes() {
        return List.of();
    }

    @Override
    public Pilote recupererPilote(Long idPilote) {
        return null;
    }

    @Override
    public Boolean archiverPilote(Long idPilote) {
        return null;
    }
}
