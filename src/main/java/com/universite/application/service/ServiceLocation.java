package com.universite.application.service;

import com.universite.Jeu;
import com.universite.Membre;
import com.universite.application.port.out.JeuRepository;
import com.universite.application.port.out.MembreRepository;
import com.universite.application.port.out.NotificationPort;
import com.universite.application.port.in.LocationJeuPort;
import java.util.Date;
import java.util.Objects;

/** Coordonne les regles de location sans dependre du web ou du stockage. */
public class ServiceLocation implements LocationJeuPort {
    private final JeuRepository jeuRepository;
    private final MembreRepository membreRepository;
    private final NotificationPort notificationPort;

    public ServiceLocation(JeuRepository jeuRepository, MembreRepository membreRepository,
                           NotificationPort notificationPort) {
        this.jeuRepository = Objects.requireNonNull(jeuRepository);
        this.membreRepository = Objects.requireNonNull(membreRepository);
        this.notificationPort = Objects.requireNonNull(notificationPort);
    }

    @Override
    public void louer(int idJeu, int idMembre, Date date) {
        Jeu jeu = jeuRepository.trouverParId(idJeu)
                .orElseThrow(() -> new IllegalArgumentException("Jeu introuvable : " + idJeu));
        Membre membre = membreRepository.trouverParId(idMembre)
                .orElseThrow(() -> new IllegalArgumentException("Membre introuvable : " + idMembre));
        if (!membre.peutLouerUnJeuSupplementaire()) {
            throw new IllegalStateException("Un membre ne peut pas louer plus de 3 jeux.");
        }
        jeu.louerPar(membre, date);
        membre.ajouterJeuLoue(jeu);
        jeuRepository.sauvegarder(jeu);
        membreRepository.sauvegarder(membre);
        notificationPort.notifierLocation(jeu, membre);
    }

    @Override
    public void retourner(int idJeu) {
        Jeu jeu = jeuRepository.trouverParId(idJeu)
                .orElseThrow(() -> new IllegalArgumentException("Jeu introuvable : " + idJeu));
        if (jeu.estDisponible()) {
            throw new IllegalStateException("Le jeu n'est pas actuellement loue.");
        }
        Membre membre = membreRepository.trouverParId(jeu.getIdMembreLoueur())
                .orElseThrow(() -> new IllegalStateException("Le membre loueur est introuvable."));
        jeu.retourner();
        membre.retirerJeuLoue(jeu);
        jeuRepository.sauvegarder(jeu);
        membreRepository.sauvegarder(membre);
        notificationPort.notifierRetour(jeu, membre);
    }
}
