package com.universite.adapter.out.memory;

import com.universite.Jeu;
import com.universite.application.port.out.JeuRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JeuRepositoryEnMemoire implements JeuRepository {
    private final Map<Integer, Jeu> jeux = new HashMap<>();
    public Optional<Jeu> trouverParId(int idJeu) { return Optional.ofNullable(jeux.get(idJeu)); }
    public List<Jeu> trouverTous() { return new ArrayList<>(jeux.values()); }
    public void sauvegarder(Jeu jeu) { jeux.put(jeu.getIdJeu(), jeu); }
    public void supprimer(int idJeu) { jeux.remove(idJeu); }
}
