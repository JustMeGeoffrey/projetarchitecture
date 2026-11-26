package com.universite.adapter.out.memory;

import com.universite.Administrateur;
import com.universite.application.port.out.AdministrateurRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AdministrateurRepositoryEnMemoire implements AdministrateurRepository {
    private final Map<String, Administrateur> administrateurs = new HashMap<>();

    public AdministrateurRepositoryEnMemoire(Administrateur... administrateursInitiaux) {
        for (Administrateur administrateur : administrateursInitiaux) {
            administrateurs.put(administrateur.getUsername(), administrateur);
        }
    }

    public Optional<Administrateur> trouverParUsername(String username) {
        return Optional.ofNullable(administrateurs.get(username));
    }
}
