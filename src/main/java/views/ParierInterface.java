package views;

import controleur.notifications.Sujet;

import java.util.Collection;

public interface ParierInterface extends Sujet {
    void show();

    void majListeEquipes(Collection<String> equipe);


    void informerUtilisateurErreur(String leMatchEstClos);
}
