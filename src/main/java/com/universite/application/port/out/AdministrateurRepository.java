package com.universite.application.port.out;

import com.universite.Administrateur;
import java.util.Optional;

public interface AdministrateurRepository {
    Optional<Administrateur> trouverParUsername(String username);
}
