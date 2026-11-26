package com.universite.application.port.out;

import com.universite.Jeu;
import com.universite.Membre;

public interface NotificationPort {
    void notifierLocation(Jeu jeu, Membre membre);
    void notifierRetour(Jeu jeu, Membre membre);
}
