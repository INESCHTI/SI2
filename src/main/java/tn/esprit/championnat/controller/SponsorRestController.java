package tn.esprit.championnat.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.championnat.entities.Sponsor;
import tn.esprit.championnat.services.ISponsorService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
public class SponsorRestController {

    private final ISponsorService sponsorService;

    // GET : récupérer tous les sponsors
    // URL: http://localhost:8089/championnat/sponsor/retrieve-all
    @GetMapping("/retrieve-all")
    public List<Sponsor> getAllSponsors() {
        return sponsorService.listSponsors();
    }

    // GET : récupérer un sponsor par ID
    // URL: http://localhost:8089/championnat/sponsor/retrieve/{id}
    @GetMapping("/retrieve/{sponsor-id}")
    public Sponsor getSponsor(@PathVariable("sponsor-id") Long sponsorId) {
        return sponsorService.recupererSponsor(sponsorId);
    }

    // POST : ajouter un sponsor
    // URL: http://localhost:8089/championnat/sponsor/add
    @PostMapping("/add")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    // POST : ajouter plusieurs sponsors
    // URL: http://localhost:8089/championnat/sponsor/add-multiple
    @PostMapping("/add-multiple")
    public List<Sponsor> addSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    // PUT : mettre à jour un sponsor
    // URL: http://localhost:8089/championnat/sponsor/update
    @PutMapping("/update")
    public Sponsor updateSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    // DELETE : supprimer un sponsor
    // URL: http://localhost:8089/championnat/sponsor/remove/{id}
    @DeleteMapping("/remove/{sponsor-id}")
    public void removeSponsor(@PathVariable("sponsor-id") Long sponsorId) {
        sponsorService.supprimerSponsor(sponsorId);
    }

    // PUT : archiver un sponsor
    // URL: http://localhost:8089/championnat/sponsor/archive/{id}
    @PutMapping("/archive/{sponsor-id}")
    public Boolean archiveSponsor(@PathVariable("sponsor-id") Long sponsorId) {
        return sponsorService.archiverSponsor(sponsorId);
    }
}
