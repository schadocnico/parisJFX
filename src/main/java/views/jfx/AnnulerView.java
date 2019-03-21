package views.jfx;

import controleur.Controleur;
import controleur.notifications.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Pari;
import views.AnnulerInterface;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class AnnulerView implements AnnulerInterface {

    private Stage primaryStage;
    Controleur controleur;
    private Scene maScene;

    @FXML
    Label monAnnulation;

    public static AnnulerInterface creerIntance(Controleur c, Stage primaryStage) {
        URL location = AnnulerView.class.getResource("/views/jfx/Annuler.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Pane root = null;
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnnulerView vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMaScene(new Scene(root, root.getPrefWidth(),root.getPrefHeight()));
        vue.setControleur(c);
        c.enregistrerSujet(vue);
        return vue;

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



    public void goToMenuPrincipal(ActionEvent actionEvent){
        controleur.goToMenuPrincipal();
    }

    public void annulerAction(ActionEvent actionEvent){
        this.controleur.annuler();
    }



    @Override
    public void show() {
        primaryStage.setTitle("Ahhhhhh");
        primaryStage.setScene(getMaScene());
        primaryStage.show();

    }

    @Override
    public void majMonAnnulation(Pari p) {
        String Newligne=System.getProperty("line.separator");
        monAnnulation.setText(p.getMatch().getSport() + " : "+ p.getMatch().getEquipe1() + " VS " + p.getMatch().getEquipe2() + Newligne +
                 "Vous avez misé pour : "+p.getVainqueur() +" mise de " + p.getMontant()+" euros."+Newligne+ "Il se déroulera le " + p.getMatch().getQuand());
    }

    @Override
    public void informerUtilisateurErreur(String con) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Annuler erreur");
            alert.setContentText(con);
            alert.show();

    }

    @Override
    public void notifier(Notification n) {
        switch (n.getTypeNotification()) {
            case ANNULER_PARI_ERREUR: {
                this.informerUtilisateurErreur(n.getMessage());
                return;
            }
        }
    }
}
