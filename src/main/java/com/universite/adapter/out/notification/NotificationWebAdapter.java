package com.universite.adapter.out.notification;

import com.universite.Jeu;
import com.universite.Membre;
import com.universite.application.port.out.NotificationPort;
import java.util.Objects;
import java.util.function.Consumer;

/** Adapter branche plus tard a la couche web (toast, websocket ou message HTTP). */
public class NotificationWebAdapter implements NotificationPort {
    private final Consumer<String> afficherMessage;
    public NotificationWebAdapter(Consumer<String> afficherMessage) {
        this.afficherMessage = Objects.requireNonNull(afficherMessage);
    }
    public void notifierLocation(Jeu jeu, Membre membre) {
        afficherMessage.accept("Location enregistree : " + jeu.getNom());
    }
    public void notifierRetour(Jeu jeu, Membre membre) {
        afficherMessage.accept("Retour enregistre : " + jeu.getNom());
    }
}
