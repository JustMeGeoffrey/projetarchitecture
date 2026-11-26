package com.universite.application.port.in;

import java.util.Date;

/** Use cases d'entree pour louer et retourner l'unique exemplaire d'un jeu. */
public interface LocationJeuPort {
    void louer(int idJeu, int idMembre, Date dateLocation);
    void retourner(int idJeu);
}
