package views.jfx;

import controleur.Controleur;
import controleur.notifications.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Pari;
import views.RecapAnnulerInterface;
import views.RecapParierInterface;

import java.io.IOException;
import java.net.URL;

public class RecapAnnulerView implements RecapAnnulerInterface {

        @FXML
        Label monAnnulation;

        private Stage primaryStage;
        Controleur controleur;
        private Scene maScene;

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

        public static RecapAnnulerInterface creerInstance(Controleur c, Stage primaryStage) {
            URL location = views.jfx.RecapAnnulerView.class.getResource("/views/jfx/RecapAnnuler.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Pane root = null;
            try {
                root = (Pane) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            views.jfx.RecapAnnulerView vue = fxmlLoader.getController();
            vue.setPrimaryStage(primaryStage);
            vue.setMaScene(new Scene(root, root.getPrefWidth(),root.getPrefHeight()));
            vue.setControleur(c);
            c.enregistrerSujet(vue);
            return vue;
        }

        public void goToMenuPrincipal(ActionEvent actionEvent) {
            controleur.goToMenuPrincipal();
        }


        @Override
        public void show() {
            primaryStage.setTitle("Recapitulatif de votre annulation");
            primaryStage.setScene(getMaScene());
            primaryStage.show();
        }

    @Override
    public void majPariAnnule(Pari p) {
        String Newligne=System.getProperty("line.separator");
        monAnnulation.setText("Votre pari : " +
                p.getMatch().getSport() + " : "+ p.getMatch().getEquipe1() + " VS " + p.getMatch().getEquipe2() + Newligne +
                "Vous avez parié pour "+ p.getVainqueur() +" votre mise est de " + p.getMontant()+" euros."+Newligne+ "Il se déroulera le " + p.getMatch().getQuand()+Newligne
        +"a été annulé");

    }


    @Override
    public void notifier(Notification n) {
        switch (n.getTypeNotification()) {
            case ANNULER_MAJ_RECAP: {
                n.maj(this);
                return;
            }
        }
    }
}

