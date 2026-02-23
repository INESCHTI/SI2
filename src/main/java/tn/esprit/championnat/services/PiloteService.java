package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.repository.PiloteRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class PiloteService implements IPiloteService{
    private  PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        piloteRepository.save(p);
        return "Pilote ajouté avec succès";
    }


}
