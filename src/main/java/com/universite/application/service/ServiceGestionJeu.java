package com.universite.application.service;

import com.universite.Jeu;
import com.universite.application.port.in.GestionJeuPort;
import com.universite.application.port.out.JeuRepository;
import java.util.List;
import java.util.Objects;

/** Centralise le CRUD et la recherche des jeux du catalogue. */
public class ServiceGestionJeu implements GestionJeuPort {
    private final JeuRepository jeuRepository;
    private final CatalogueJeuService catalogueJeuService;

    public ServiceGestionJeu(JeuRepository jeuRepository, CatalogueJeuService catalogueJeuService) {
        this.jeuRepository = Objects.requireNonNull(jeuRepository);
        this.catalogueJeuService = Objects.requireNonNull(catalogueJeuService);
    }

    @Override
    public void ajouter(Jeu jeu) {
        Objects.requireNonNull(jeu, "Le jeu est obligatoire.");
        if (jeuRepository.trouverParId(jeu.getIdJeu()).isPresent()) {
            throw new IllegalArgumentException("Un jeu avec cet identifiant existe deja.");
        }
        jeuRepository.sauvegarder(jeu);
    }

    @Override
    public void modifier(Jeu jeu) {
        Objects.requireNonNull(jeu, "Le jeu est obligatoire.");
        if (jeuRepository.trouverParId(jeu.getIdJeu()).isEmpty()) {
            throw new IllegalArgumentException("Jeu introuvable : " + jeu.getIdJeu());
        }
        jeuRepository.sauvegarder(jeu);
    }

    @Override
    public void retirer(int idJeu) {
        Jeu jeu = jeuRepository.trouverParId(idJeu)
                .orElseThrow(() -> new IllegalArgumentException("Jeu introuvable : " + idJeu));
        if (!jeu.estDisponible()) {
            throw new IllegalStateException("Impossible de retirer un jeu actuellement loue.");
        }
        jeuRepository.supprimer(idJeu);
    }

    @Override
    public List<Jeu> lister() {
        return jeuRepository.trouverTous();
    }

    @Override
    public List<Jeu> rechercher(int nombreJoueurs, int age, Integer difficulte, String categorie) {
        return catalogueJeuService.rechercher(nombreJoueurs, age, difficulte, categorie);
    }
}
