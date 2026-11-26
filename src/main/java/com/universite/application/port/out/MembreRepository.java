package com.universite.application.port.out;

import com.universite.Membre;
import java.util.List;
import java.util.Optional;

public interface MembreRepository {
    Optional<Membre> trouverParId(int idMembre);
    List<Membre> trouverTous();
    void sauvegarder(Membre membre);
    void supprimer(int idMembre);
}
