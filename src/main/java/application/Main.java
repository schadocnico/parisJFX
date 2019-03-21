package application;

import controleur.Controleur;
import facade.FacadeParis;
import facade.FacadeParisStaticImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import views.FabriqueVues;
import views.jfx.FabriqueVuesJFX;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FabriqueVues fabriqueVues = new FabriqueVuesJFX(primaryStage);
        FacadeParis modele = new FacadeParisStaticImpl();
        Controleur monControleur = new Controleur(fabriqueVues,modele);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
