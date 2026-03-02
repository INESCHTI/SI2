package tn.esprit.championnat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.services.IPiloteService;
import tn.esprit.championnat.services.PiloteServiceImplementation;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pilote")
@Tag(name = "Pilote API", description = "Gestion des pilotes")

public class PiloteRestController {

    private final IPiloteService piloteService;
    @Operation(summary = "Ajouter un pilote", description = "Ajoute un pilote au championnat")

    // Ajouter un pilote
    // POST http://localhost:8089/championnat/pilote/add-pilote
    @PostMapping("/add-pilote")
    public String addPilote(@RequestBody Pilote p) {
        return piloteService.addPilote(p);
    }

    @Operation(summary = "Ajouter plusieurs pilotes", description = "Ajoute une liste de pilotes")
    // Ajouter plusieurs pilotes
    @PostMapping("/add-multiple")
    public List<Pilote> addPilotes(@RequestBody List<Pilote> pilotes) {
        return ((PiloteServiceImplementation) piloteService).addPilotes(pilotes);
    }


    @Operation(summary = "Supprimer tous les pilotes", description = "Supprime tous les pilotes du championnat")
    // Supprimer tous les pilotes
    @DeleteMapping("/remove-all")
    public void removeAllPilotes() {
        piloteService.deleteAllPilotes();
    }

}
