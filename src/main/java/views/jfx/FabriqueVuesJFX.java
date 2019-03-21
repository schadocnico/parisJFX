package views.jfx;

import controleur.Controleur;
import javafx.stage.Stage;
import views.*;

import java.awt.*;

public class FabriqueVuesJFX implements FabriqueVues {

    Stage primaryStage;

    public FabriqueVuesJFX(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public MenuPrincipalInterface buildMenuPrincipalView(Controleur c) {
        return MenuPrincipal.creerInstance(c,primaryStage);

    }

    @Override
    public AccueilInterface buildAccueilView(Controleur c) {
        return AccueilView.creerInstance(c,primaryStage);
    }

    @Override
    public ParisOuvertsInterface buildParisOuvertsView(Controleur c) {
        return ParisOuvertsView.creerInstance(c,primaryStage);
    }

    @Override
    public MesParisInterface buildMesParisView(Controleur c) {
        return MesParisView.creerInstance(c,primaryStage);
    }

    @Override
    public ParierInterface buildParierView(Controleur c) {
        return ParierView.creerInstance(c,primaryStage);
    }

    @Override
    public RecapParierInterface buildRecapParierView(Controleur c) {
        return RecapParierView.creerInstance(c,primaryStage);
    }

    @Override
    public RecapAnnulerInterface buildRecapAnnulerView(Controleur c) {
        return RecapAnnulerView.creerInstance(c,primaryStage);
    }

    @Override
    public AnnulerInterface buildAnnulerView(Controleur c) {
        return AnnulerView.creerIntance(c,primaryStage);
    }

}
