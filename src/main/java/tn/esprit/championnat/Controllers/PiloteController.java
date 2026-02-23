package tn.esprit.championnat.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
        import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.services.IPiloteService;

@RestController
@RequestMapping("/pilote")
@RequiredArgsConstructor
public class PiloteController {

    private final IPiloteService piloteService;

    @PostMapping("/add")
    public String addPilote(@RequestBody Pilote p) {
        return piloteService.addPilote(p);
    }
}