package views;

import controleur.notifications.Sujet;

public interface AccueilInterface extends Sujet {

    void show();

    void informeUtilisateurErreur(String message);
}
