package com.universite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Membre {

    int idMembre;
    String nom;
    String prenom;
    String email;
    int telephone;
    Date dateInscription;
    private final List<Jeu> jeuxLoues = new ArrayList<>();

    public int getIdMembre() {
        return idMembre;
    }

    public boolean peutLouerUnJeuSupplementaire() {
        return jeuxLoues.size() < 3;
    }

    public void ajouterJeuLoue(Jeu jeu) {
        if (!jeuxLoues.contains(jeu)) {
            jeuxLoues.add(jeu);
        }
    }

    public void retirerJeuLoue(Jeu jeu) {
        jeuxLoues.remove(jeu);
    }

    public List<Jeu> getJeuxLoues() {
        return Collections.unmodifiableList(jeuxLoues);
    }

}
