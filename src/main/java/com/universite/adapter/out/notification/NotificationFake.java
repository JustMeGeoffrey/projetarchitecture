package com.universite.adapter.out.notification;

import com.universite.Jeu;
import com.universite.Membre;
import com.universite.application.port.out.NotificationPort;
import java.util.ArrayList;
import java.util.List;

/** Adapter deterministe utilise par les tests. */
public class NotificationFake implements NotificationPort {
    private final List<String> messages = new ArrayList<>();
    public void notifierLocation(Jeu jeu, Membre membre) { messages.add("LOCATION:" + jeu.getIdJeu()); }
    public void notifierRetour(Jeu jeu, Membre membre) { messages.add("RETOUR:" + jeu.getIdJeu()); }
    public List<String> getMessages() { return List.copyOf(messages); }
}
