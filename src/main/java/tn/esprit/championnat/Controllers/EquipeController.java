package tn.esprit.championnat.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.championnat.entities.Equipe;
import tn.esprit.championnat.services.IEquipeService;

@RestController
@RequestMapping("/equipe")
@RequiredArgsConstructor
public class EquipeController {

    private final IEquipeService equipeService;

    @PostMapping("/add")
    public Equipe addEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }
}