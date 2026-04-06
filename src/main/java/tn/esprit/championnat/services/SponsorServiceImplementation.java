package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Sponsor;
import tn.esprit.championnat.repositories.ContratRepository;
import tn.esprit.championnat.repositories.SponsorRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class SponsorServiceImplementation implements ISponsorService{
    private SponsorRepository sponsorRepository;
    private ContratRepository contratRepository;


    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        sponsor.setDateCreation(LocalDate.now());
        sponsor.setArchived(false);
        sponsor.setBloquerContrat(false);
        return sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        sponsors.forEach(s -> {
            s.setDateCreation(LocalDate.now());
            s.setArchived(false);
            s.setBloquerContrat(false);
        });
        return sponsorRepository.saveAll(sponsors);
    }
    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        sponsor.setDateDerniereModification(LocalDate.now());
        return sponsorRepository.save(sponsor);
    }

    @Override
    public void supprimerSponsor(Long idSponsor) {
        sponsorRepository.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sponsorRepository.findAll();

    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        return sponsorRepository.findById(idSponsor).orElse(null);
    }

    @Override
    public Boolean archiverSponsor(Long idSponsor) {
        Sponsor sponsor = sponsorRepository.findById(idSponsor).orElse(null);
        if (sponsor != null) {
            sponsor.setArchived(true);
            sponsorRepository.save(sponsor);
            return true;
        }
        return false;
    }
    @Override
    public Float pourcentageBudgetAnnuelConsomme(Long idSponsor, String annee) {
        Sponsor sponsor = sponsorRepository.findById(idSponsor).orElse(null);
        if (sponsor == null || sponsor.getBudgetAnnuel() == null || sponsor.getBudgetAnnuel() == 0) {
            return 0f;
        }

        Float montantTotal = contratRepository.sommeMontantsContratsSponsorParAnnee(idSponsor, annee);
        if (montantTotal == null) montantTotal = 0f;

        return (montantTotal / sponsor.getBudgetAnnuel()) * 100;
    }
}
