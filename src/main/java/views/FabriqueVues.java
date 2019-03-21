package views;

import controleur.Controleur;

public interface FabriqueVues {

    MenuPrincipalInterface buildMenuPrincipalView(Controleur c);

    AccueilInterface buildAccueilView(Controleur c);

    ParisOuvertsInterface buildParisOuvertsView(Controleur c);

    MesParisInterface buildMesParisView(Controleur c);

    ParierInterface buildParierView(Controleur c);

    RecapParierInterface buildRecapParierView(Controleur c);

    RecapAnnulerInterface buildRecapAnnulerView(Controleur c);

    AnnulerInterface buildAnnulerView(Controleur c);

}
