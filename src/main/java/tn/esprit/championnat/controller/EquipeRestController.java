package tn.esprit.championnat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.services.IContratService;
import tn.esprit.championnat.services.IEquipeService;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {

    private final IEquipeService equipeService;
    private final IContratService contratService;

    // Ajouter une équipe
    // POST http://localhost:8089/championnat/equipe/add
    @PostMapping("/add")
    public Equipe ajouterEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }

    // GET http://localhost:8089/championnat/equipe/historique-contrats/{libelleEquipe}
    @GetMapping("/historique-contrats/{libelleEquipe}")
    public HashMap<String, Float> historiqueContratsEquipe(@PathVariable String libelleEquipe) {
        return contratService.historiqueContratsEquipe(libelleEquipe);
    }

    // GET http://localhost:8089/championnat/equipe/points/{idEquipe}/{idChampionnat}/{annee}
    @GetMapping("/points/{idEquipe}/{idChampionnat}/{annee}")
    public Integer nbPointsParPilotesUneEquipeChampionnatPourUneAnne(@PathVariable Long idEquipe,
                                                                      @PathVariable Long idChampionnat,
                                                                      @PathVariable String annee) {
        return equipeService.nbPointsParPilotesUneEquipeChampionnatPourUneAnne(idEquipe, idChampionnat, annee);
    }
}
