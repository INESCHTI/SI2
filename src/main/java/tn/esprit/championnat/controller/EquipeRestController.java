package tn.esprit.championnat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.services.IEquipeService;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {

    private final IEquipeService equipeService;

    // Ajouter une équipe
    // POST http://localhost:8089/championnat/equipe/add
    @PostMapping("/add")
    public Equipe ajouterEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }

}
