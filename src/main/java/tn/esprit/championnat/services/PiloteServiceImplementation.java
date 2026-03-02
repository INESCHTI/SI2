package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.repositories.PiloteRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class PiloteServiceImplementation implements IPiloteService {
    private PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        piloteRepository.save(p);
        return "Pilote ajouté avec succès";
    }
    public List<Pilote> addPilotes(List<Pilote> pilotes) {
        return piloteRepository.saveAll(pilotes);
    }

    @Override
    public void deleteAllPilotes() {
        piloteRepository.deleteAll();
    }



}
