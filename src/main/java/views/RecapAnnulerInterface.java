package views;

import controleur.notifications.Sujet;
import modele.Pari;

public interface RecapAnnulerInterface extends Sujet {
    void show();
    void majPariAnnule(Pari pari);

}
