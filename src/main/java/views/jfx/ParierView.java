package views.jfx;

import controleur.Controleur;
import controleur.notifications.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.Match;
import views.ParierInterface;
import views.ParisOuvertsInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class ParierView implements ParierInterface{


    private Stage primaryStage;
    Controleur controleur;
    private Scene maScene;

    @FXML
    TextField mise;

    @FXML
    ComboBox equipeGagnante;

    public ComboBox getEquipeGagnante() {
        return equipeGagnante;
    }

    public TextField getMise() {
        return mise;
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void setMaScene(Scene maScene) {
        this.maScene = maScene;
    }

    public Scene getMaScene() {
        return maScene;
    }


    public static ParierInterface creerInstance(Controleur c, Stage primaryStage) {
        URL location = ParierView.class.getResource("/views/jfx/Parier.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Pane root = null;
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ParierView vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMaScene(new Scene(root, root.getPrefWidth(),root.getPrefHeight()));
        vue.setControleur(c);
        c.enregistrerSujet(vue);
        return vue;
    }

    public void parierAction(ActionEvent actionEvent){
        double mise = Double.parseDouble(getMise().getText());
        System.out.println(mise);
        String equipe = (String) getEquipeGagnante().getValue();
        System.out.println(equipe);
        this.controleur.parier(mise, equipe);
    }


    @Override
    public void show() {
        primaryStage.setTitle("Pariez");
        primaryStage.setScene(getMaScene());
        primaryStage.show();
    }

    @Override
    public void majListeEquipes(Collection<String> equipe) {
        System.out.println(equipe+"oulaoup");
        ObservableList<String> liste = FXCollections.observableArrayList(equipe);
            this.equipeGagnante.setItems(liste);
    }

    @Override
    public void informerUtilisateurErreur(String leMatchEstClos) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur pari");
        alert.setContentText(leMatchEstClos);
        alert.show();

    }


    public void goToMenuPrincipal(ActionEvent actionEvent) {
        controleur.goToMenuPrincipal();
    }


    @Override
    public void notifier(Notification n) {
        switch (n.getTypeNotification()) {
            case PARIER_ERREUR: {
                this.informerUtilisateurErreur(n.getMessage());
                return;
            }

            case PARIS_MAJ_EQUIPES: {
                n.maj(this);
                return;
            }

        }
    }
}
