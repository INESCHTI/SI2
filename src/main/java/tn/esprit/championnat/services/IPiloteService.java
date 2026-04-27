package tn.esprit.championnat.services;

import tn.esprit.championnat.entities.Pilote;

import java.time.LocalDate;

public interface IPiloteService {
    String addPilote(Pilote p);

    void deleteAllPilotes();

    Pilote affecterPiloteAEquipe(String libP, String libE);

    Float moyennePositionsEntreDeuxDate(LocalDate startDate, LocalDate endDate, String libelleP);
}
