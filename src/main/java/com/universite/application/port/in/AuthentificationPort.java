package com.universite.application.port.in;

/** Use case d'entree pour la connexion de l'administrateur. */
public interface AuthentificationPort {
    boolean authentifier(String username, String mdp);
}
