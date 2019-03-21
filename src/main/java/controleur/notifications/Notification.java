package controleur.notifications;

import controleur.notifications.update.NotificationUpdateParierInterface;
import controleur.notifications.update.NotificationUpdateRecapAnnulerInterface;
import controleur.notifications.update.Visiteur;
import modele.Pari;

import java.util.ArrayList;
import java.util.Collection;

public interface Notification extends Visiteur {

    static Notification creerMajEquipe(Collection<String> liste) {
        return new NotificationUpdateParierInterface(liste);
    }

    static Notification creerMajAnnulationPari(Pari pariAnnuler) {
        return new NotificationUpdateRecapAnnulerInterface(pariAnnuler);
    }


    enum TypeNotification {CONNEXION_ERREUR, ANNULER_PARI_ERREUR, PARIER_ERREUR, ANNULER_MAJ_RECAP, PARIS_MAJ_EQUIPES}


  static Notification creer(String message, TypeNotification typeNotification) {
      return new NotificationImpl(message, typeNotification);
  }

  static Notification creer(TypeNotification typeNotification) {
        return new NotificationImpl(typeNotification);
  }

    String getMessage();
    TypeNotification getTypeNotification();


}
