package com.universite.application.service;

import com.universite.Jeu;
import com.universite.Membre;
import com.universite.application.port.in.MembrePort;
import com.universite.application.port.out.MembreRepository;
import java.util.List;
import java.util.Objects;

/** Centralise la gestion des membres et protege les locations en cours. */
public class ServiceMembre implements MembrePort {
    private final MembreRepository membreRepository;

    public ServiceMembre(MembreRepository membreRepository) {
        this.membreRepository = Objects.requireNonNull(membreRepository);
    }

    @Override
    public void ajouter(Membre membre) {
        Objects.requireNonNull(membre, "Le membre est obligatoire.");
        if (membreRepository.trouverParId(membre.getIdMembre()).isPresent()) {
            throw new IllegalArgumentException("Un membre avec cet identifiant existe deja.");
        }
        membreRepository.sauvegarder(membre);
    }

    @Override
    public void modifier(Membre membre) {
        Objects.requireNonNull(membre, "Le membre est obligatoire.");
        if (membreRepository.trouverParId(membre.getIdMembre()).isEmpty()) {
            throw new IllegalArgumentException("Membre introuvable : " + membre.getIdMembre());
        }
        membreRepository.sauvegarder(membre);
    }

    @Override
    public void retirer(int idMembre) {
        Membre membre = membreRepository.trouverParId(idMembre)
                .orElseThrow(() -> new IllegalArgumentException("Membre introuvable : " + idMembre));
        if (!membre.getJeuxLoues().isEmpty()) {
            throw new IllegalStateException("Impossible de retirer un membre ayant des jeux loues.");
        }
        membreRepository.supprimer(idMembre);
    }

    @Override
    public List<Jeu> consulterJeuxLoues(int idMembre) {
        return membreRepository.trouverParId(idMembre)
                .orElseThrow(() -> new IllegalArgumentException("Membre introuvable : " + idMembre))
                .getJeuxLoues();
    }
}
