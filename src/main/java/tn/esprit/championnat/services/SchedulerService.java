package tn.esprit.championnat.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.championnat.entities.Categorie;
import tn.esprit.championnat.entities.Contrat;
import tn.esprit.championnat.entities.Pilote;
import tn.esprit.championnat.entities.Sponsor;
import tn.esprit.championnat.repositories.ContratRepository;
import tn.esprit.championnat.repositories.PiloteRepository;
import tn.esprit.championnat.repositories.SponsorRepository;

import java.time.Year;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SchedulerService {

    private final ContratRepository contratRepository;
    private final SponsorRepository sponsorRepository;
    private final PiloteRepository piloteRepository;
    private final ISponsorService sponsorService;

    // 5.1 toutes les 30 secondes
    @Scheduled(fixedRate = 30000)
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {
        log.info("========== Début scheduler 5.1 : archivage des contrats expirés + affichage des contrats actifs ==========");

        int anneeCourante = Year.now().getValue();
        int nbArchives = 0;

        List<Contrat> contrats = contratRepository.findAll();
        log.info("Nombre total de contrats trouvés : {}", contrats.size());

        for (Contrat c : contrats) {
            if (c.getAnnee() == null) {
                log.warn("Contrat {} ignoré : année nulle.", c.getIdContrat());
                continue;
            }

            try {
                int anneeContrat = Integer.parseInt(c.getAnnee());

                if (anneeContrat < anneeCourante && !Boolean.TRUE.equals(c.getArchived())) {
                    c.setArchived(true);
                    contratRepository.save(c);
                    nbArchives++;

                    log.info("Contrat {} archivé car son année ({}) est inférieure à l'année courante ({}).",
                            c.getIdContrat(), anneeContrat, anneeCourante);
                }

            } catch (NumberFormatException e) {
                log.warn("Année invalide pour le contrat {} : '{}'", c.getIdContrat(), c.getAnnee());
            }
        }

        log.info("Nombre de contrats archivés dans cette exécution : {}", nbArchives);

        List<Contrat> actifs = contratRepository.findActiveContrats();

        if (actifs.isEmpty()) {
            log.info("Aucun contrat actif trouvé.");
        } else {
            log.info("Liste des contrats actifs :");
            for (Contrat c : actifs) {
                if (c.getEquipe() != null && c.getSponsor() != null && c.getMontant() != null) {
                    log.info("L'équipe {} a un contrat d'un montant de {} avec le sponsor {}",
                            c.getEquipe().getLibelle(),
                            c.getMontant(),
                            c.getSponsor().getNom());
                } else {
                    log.warn("Contrat actif {} incomplet : équipe, sponsor ou montant manquant.", c.getIdContrat());
                }
            }
        }

        log.info("========== Fin scheduler 5.1 ==========");
    }

    @Scheduled(cron = "0 15 11 31 12 *")
    public void mettreAJourPointsEtClassementPilotesFormula1() {
        log.info("========== Début scheduler 5.2 : mise à jour du classement FORMULA1 ==========");

        List<Pilote> pilotes = piloteRepository.findByCategorieOrderByNbPointsTotalDesc(Categorie.FORMULA1);

        if (pilotes == null || pilotes.isEmpty()) {
            log.warn("Aucun pilote trouvé pour la catégorie FORMULA1.");
            log.info("========== Fin scheduler 5.2 ==========");
            return;
        }

        int classement = 1;
        for (Pilote p : pilotes) {
            if (p.getNbPointsTotal() == null) {
                p.setNbPointsTotal(0);
            }

            p.setClassementGeneral(classement);
            piloteRepository.save(p);

            log.info("{} - {} avec {} points",
                    classement,
                    p.getLibelleP(),
                    p.getNbPointsTotal());

            classement++;
        }

        log.info("Classement général des pilotes FORMULA1 mis à jour avec succès.");
        log.info("========== Fin scheduler 5.2 ==========");
    }

    @Scheduled(cron = "0 0 9 * * MON")
    public void afficherPourcentageBudgetSponsors() {
        log.info("========== Début scheduler 5.3 : affichage du budget des sponsors ==========");

        String anneeCourante = String.valueOf(Year.now().getValue());
        List<Sponsor> sponsors = sponsorRepository.findAll();

        if (sponsors.isEmpty()) {
            log.warn("Aucun sponsor trouvé.");
            log.info("========== Fin scheduler 5.3 ==========");
            return;
        }

        for (Sponsor sponsor : sponsors) {
            Float pourcentage = sponsorService.pourcentageBudgetAnnuelConsomme(
                    sponsor.getIdSponsor(), anneeCourante);

            if (pourcentage == null) {
                pourcentage = 0f;
            }

            log.info("Sponsor {} : {} % du budget annuel consommé",
                    sponsor.getNom(), pourcentage);

            if (pourcentage > 70 && pourcentage < 100) {
                log.warn("attention budget presque consommé : {} % !", pourcentage);
            } else if (pourcentage >= 100) {
                log.error("budget dépassé!! vous ne pouvez plus faire de contrats");
                sponsor.setBloquerContrat(true);
                sponsorRepository.save(sponsor);
                log.info("Le champ bloquerContrat du sponsor {} a été mis à true.", sponsor.getNom());
            } else {
                log.info("Le budget du sponsor {} est encore dans une zone normale.", sponsor.getNom());
            }
        }

        log.info("========== Fin scheduler 5.3 ==========");
    }

    // Méthodes de test manuel sans attendre les horaires du scheduler
    public void testerScheduler51() {
        archiverContratsExpireesEtAffichageContratsActifsParEquipe();
    }

    public void testerScheduler52() {
        mettreAJourPointsEtClassementPilotesFormula1();
    }

    public void testerScheduler53() {
        afficherPourcentageBudgetSponsors();
    }
}