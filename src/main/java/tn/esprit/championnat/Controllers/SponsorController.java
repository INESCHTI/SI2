package tn.esprit.championnat.Controllers;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.championnat.entities.Sponsor;
import tn.esprit.championnat.services.ISponsorService;

import java.util.List;

@Controller
@RequestMapping("/sponsor")
@AllArgsConstructor
public class SponsorController {

    private final ISponsorService sponsorService;

    @PostMapping("/add")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    @PostMapping("/addAll")
    public List<Sponsor> addSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    @PutMapping("/update")
    public Sponsor updateSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSponsor(@PathVariable Long id) {
        sponsorService.supprimerSponsor(id);
    }

    @GetMapping("/all")
    public List<Sponsor> getAll() {
        return sponsorService.listSponsors();
    }

    @GetMapping("/{id}")
    public Sponsor getSponsor(@PathVariable Long id) {
        return sponsorService.recupererSponsor(id);
    }

    @PutMapping("/archive/{id}")
    public Boolean archiveSponsor(@PathVariable Long id) {
        return sponsorService.archiverSponsor(id);
    }
}

