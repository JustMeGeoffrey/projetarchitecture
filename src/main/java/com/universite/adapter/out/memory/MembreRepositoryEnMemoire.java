package com.universite.adapter.out.memory;

import com.universite.Membre;
import com.universite.application.port.out.MembreRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MembreRepositoryEnMemoire implements MembreRepository {
    private final Map<Integer, Membre> membres = new HashMap<>();
    public Optional<Membre> trouverParId(int idMembre) { return Optional.ofNullable(membres.get(idMembre)); }
    public java.util.List<Membre> trouverTous() { return new ArrayList<>(membres.values()); }
    public void sauvegarder(Membre membre) { membres.put(membre.getIdMembre(), membre); }
    public void supprimer(int idMembre) { membres.remove(idMembre); }
}
