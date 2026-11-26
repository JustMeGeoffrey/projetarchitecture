package com.universite;

import java.util.Date;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Jeu {

    private static final int AUCUN_MEMBRE_LOUEUR = -1;

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
    int idMembreLoueur = AUCUN_MEMBRE_LOUEUR;
    private final Set<String> categories = new LinkedHashSet<>();

    /**
     * Un jeu est disponible seulement si aucune location n'est en cours.
     */
    public boolean estDisponible() {
        return idMembreLoueur == AUCUN_MEMBRE_LOUEUR
                && dateLocation == null;
    }

    /**
     * Enregistre la location de l'unique exemplaire de ce jeu par un membre.
     *
     * @throws IllegalStateException si le jeu est deja loue
     * @throws NullPointerException si le membre ou la date est absent
     */
    public void louerPar(Membre membre, Date date) {
        Objects.requireNonNull(membre, "Le membre est obligatoire.");
        Objects.requireNonNull(date, "La date de location est obligatoire.");

        if (!estDisponible()) {
            throw new IllegalStateException("Le jeu est deja loue.");
        }

        idMembreLoueur = membre.getIdMembre();
        dateLocation = new Date(date.getTime());
    }

    /**
     * Clot la location en cours et rend le jeu de nouveau disponible.
     *
     * @throws IllegalStateException si le jeu n'est pas loue
     */
    public void retourner() {
        if (estDisponible()) {
            throw new IllegalStateException("Le jeu n'est pas actuellement loue.");
        }

        idMembreLoueur = AUCUN_MEMBRE_LOUEUR;
        dateLocation = null;
    }

    public int getIdJeu() {
        return idJeu;
    }

    public int getIdMembreLoueur() {
        return idMembreLoueur;
    }

    public String getNom() {
        return nom;
    }

    public int getNbJoueursMin() {
        return nbJoueursMin;
    }

    public int getNbJoueursMax() {
        return nbJoueursMax;
    }

    public int getAgeConseille() {
        return ageConseille;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public Set<String> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void definirCategories(Set<String> nouvellesCategories) {
        Objects.requireNonNull(nouvellesCategories, "Les categories sont obligatoires.");
        categories.clear();
        nouvellesCategories.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(categorie -> !categorie.isEmpty())
                .map(String::toLowerCase)
                .forEach(categories::add);
    }

    public boolean correspondA(int nombreJoueurs, int age, Integer difficulteRecherchee, String categorieRecherchee) {
        boolean joueursCompatibles = nombreJoueurs >= nbJoueursMin && nombreJoueurs <= nbJoueursMax;
        boolean ageCompatible = age >= ageConseille;
        boolean difficulteCompatible = difficulteRecherchee == null || difficulte == difficulteRecherchee;
        boolean categorieCompatible = categorieRecherchee == null
                || categorieRecherchee.isBlank()
                || categories.contains(categorieRecherchee.trim().toLowerCase());
        return joueursCompatibles && ageCompatible && difficulteCompatible && categorieCompatible;
    }

}
