package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.repository.PiloteRepository;

import java.util.List;

public class PiloteService implements IPiloteService{
    private  PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        piloteRepository.save(p);
        return "Pilote ajouté avec succès";
    }


}
