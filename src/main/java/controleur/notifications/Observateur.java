package controleur.notifications;

public interface Observateur {

    void enregistrerSujet(Sujet s);
    void broadCastNotification(Notification n);
}
