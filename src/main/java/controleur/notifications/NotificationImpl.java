package controleur.notifications;

import views.*;

public class NotificationImpl implements Notification {

    String message;
    TypeNotification typeNotification;

    protected NotificationImpl(String message, TypeNotification typeNotification) {
        this.message = message;
        this.typeNotification = typeNotification;
    }

    protected NotificationImpl(TypeNotification typeNotification) {
        this("",typeNotification);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    @Override
    public void maj(ParierInterface parierInterface) {

    }

    @Override
    public void maj(MesParisInterface parisInterface) {

    }

    @Override
    public void maj(AnnulerInterface annulerInterface) {

    }

    @Override
    public void maj(ParisOuvertsInterface parisOuvertsInterface) {

    }

    @Override
    public void maj(RecapParierInterface recapParierInterface) {

    }

    @Override
    public void maj(RecapAnnulerInterface recapAnnulerInterface) {

    }
}
