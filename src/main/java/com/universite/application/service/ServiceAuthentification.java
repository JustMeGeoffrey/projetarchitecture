package com.universite.application.service;

import com.universite.application.port.in.AuthentificationPort;
import com.universite.application.port.out.AdministrateurRepository;
import java.util.Objects;

/** Verifie les identifiants sans connaitre l'adapter de stockage. */
public class ServiceAuthentification implements AuthentificationPort {
    private final AdministrateurRepository administrateurRepository;

    public ServiceAuthentification(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = Objects.requireNonNull(administrateurRepository);
    }

    @Override
    public boolean authentifier(String username, String mdp) {
        if (username == null || mdp == null) {
            return false;
        }
        return administrateurRepository.trouverParUsername(username)
                .map(administrateur -> administrateur.identifiantsSontValides(username, mdp))
                .orElse(false);
    }
}
