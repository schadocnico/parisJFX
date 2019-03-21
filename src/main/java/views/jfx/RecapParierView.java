package views.jfx;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Pari;
import views.RecapParierInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class RecapParierView implements RecapParierInterface {

    @FXML
    Label monPari;

    private Stage primaryStage;
    Controleur controleur;
    private Scene maScene;

    public Label getMonPari() {
        return monPari;
    }

    public void setMonPari(Label monPari) {
        this.monPari = monPari;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public Scene getMaScene() {
        return maScene;
    }

    public void setMaScene(Scene maScene) {
        this.maScene = maScene;
    }

    public static RecapParierInterface creerInstance(Controleur c, Stage primaryStage) {
        URL location = RecapParierView.class.getResource("/views/jfx/RecapParier.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Pane root = null;
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RecapParierView vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMaScene(new Scene(root, root.getPrefWidth(),root.getPrefHeight()));
        vue.setControleur(c);
        return vue;
    }

    public void goToMenuPrincipal(ActionEvent actionEvent) {
        controleur.goToMenuPrincipal();
    }




    @Override
    public void show() {
        primaryStage.setTitle("Recapitulatif de votre paris");
        primaryStage.setScene(getMaScene());
        primaryStage.show();
    }

    @Override
    public void majPari(Pari p) {
        String Newligne=System.getProperty("line.separator");
        monPari.setText("Vous avez parier pour le match : " +
                p.getMatch().getSport() + " : "+ p.getMatch().getEquipe1() + " VS " + p.getMatch().getEquipe2() + Newligne +
                "Vous avez parié pour "+ p.getVainqueur() +" votre mise est de " + p.getMontant()+" euros."+Newligne+ "Il se déroulera le " + p.getMatch().getQuand());
            }
}
