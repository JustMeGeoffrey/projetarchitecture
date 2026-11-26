package com.universite;

public class Administrateur {

    String mdp;
    String username;

    public String getUsername() {
        return username;
    }

    public boolean identifiantsSontValides(String usernameSaisi, String motDePasseSaisi) {
        return username != null
                && mdp != null
                && username.equals(usernameSaisi)
                && mdp.equals(motDePasseSaisi);
    }

}
