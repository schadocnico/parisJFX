package controleur.notifications.update;

import controleur.notifications.NotificationImpl;
import modele.Pari;
import views.AnnulerInterface;
import views.ParierInterface;
import views.RecapAnnulerInterface;

import java.util.Collection;

public class NotificationUpdateRecapAnnulerInterface extends NotificationImpl {


    Pari pari;
    public NotificationUpdateRecapAnnulerInterface(Pari pari) {
        super(TypeNotification.ANNULER_MAJ_RECAP);
        this.pari = pari;
    }


    @Override
    public void maj(RecapAnnulerInterface annulerInterface) {
        annulerInterface.majPariAnnule(pari);
    }
}
