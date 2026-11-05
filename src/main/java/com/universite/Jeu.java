package com.universite;

import java.util.Date;

public class Jeu {

    int idJeu;
    String nom;
    String description;
    int nbJoueursMin;
    int nbJoueursMax;
    int difficulte;
    int anneeSortie;
    int ageConseille;
    String editeur;
    String auteur;
    int dureePartie;
    Date dateLocation;
    String resume;
    int idMembreLoueur; // Vaut -1 si le jeu n'est pas loué
    String[] categories;

}
