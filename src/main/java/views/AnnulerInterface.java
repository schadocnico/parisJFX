package views;

import controleur.Controleur;
import controleur.notifications.Sujet;
import javafx.stage.Stage;
import modele.Pari;

public interface AnnulerInterface extends Sujet {
    void show();
    void majMonAnnulation(Pari pari);

    void informerUtilisateurErreur(String con);
}
