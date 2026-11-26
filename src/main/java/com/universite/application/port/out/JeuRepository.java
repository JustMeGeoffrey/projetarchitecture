package com.universite.application.port.out;

import com.universite.Jeu;
import java.util.List;
import java.util.Optional;

public interface JeuRepository {
    Optional<Jeu> trouverParId(int idJeu);
    List<Jeu> trouverTous();
    void sauvegarder(Jeu jeu);
    void supprimer(int idJeu);
}
