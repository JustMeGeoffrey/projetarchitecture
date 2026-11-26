package com.universite.application.port.in;

import com.universite.Jeu;
import com.universite.Membre;
import java.util.List;

/** Use cases d'entree pour administrer les membres et consulter leurs emprunts. */
public interface MembrePort {
    void ajouter(Membre membre);
    void modifier(Membre membre);
    void retirer(int idMembre);
    List<Jeu> consulterJeuxLoues(int idMembre);
}
