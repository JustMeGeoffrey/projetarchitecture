package com.universite.application.port.in;

import com.universite.Jeu;
import java.util.List;

/** Use cases d'entree de gestion et de recherche dans le catalogue. */
public interface GestionJeuPort {
    void ajouter(Jeu jeu);
    void modifier(Jeu jeu);
    void retirer(int idJeu);
    List<Jeu> lister();
    List<Jeu> rechercher(int nombreJoueurs, int age, Integer difficulte, String categorie);
}
