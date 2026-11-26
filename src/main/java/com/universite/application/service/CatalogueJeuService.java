package com.universite.application.service;

import com.universite.Jeu;
import com.universite.application.port.out.JeuRepository;
import java.util.List;
import java.util.Objects;

/** Recherche les jeux compatibles avec les contraintes d'un groupe. */
public class CatalogueJeuService {
    private final JeuRepository jeuRepository;

    public CatalogueJeuService(JeuRepository jeuRepository) {
        this.jeuRepository = Objects.requireNonNull(jeuRepository);
    }

    public List<Jeu> rechercher(int nombreJoueurs, int age, Integer difficulte, String categorie) {
        return jeuRepository.trouverTous().stream()
                .filter(jeu -> jeu.correspondA(nombreJoueurs, age, difficulte, categorie))
                .toList();
    }
}
