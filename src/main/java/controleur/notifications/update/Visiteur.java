package controleur.notifications.update;

import views.*;

public interface Visiteur {

    void maj(ParierInterface parierInterface);
    void maj(MesParisInterface parisInterface);
    void maj(AnnulerInterface annulerInterface);
    void maj(ParisOuvertsInterface parisOuvertsInterface);
    void maj(RecapParierInterface recapParierInterface);
    void maj(RecapAnnulerInterface recapAnnulerInterface);


}
