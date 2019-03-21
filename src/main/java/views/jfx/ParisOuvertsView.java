package views.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.Match;
import modele.Pari;
import views.ParisOuvertsInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class ParisOuvertsView implements ParisOuvertsInterface {
    @FXML
    ListView<Match> listeAllParis;

    @FXML
    VBox topNiveau;

    private Stage primaryStage;
    Controleur controleur;
    private Scene maScene;

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


    public static ParisOuvertsInterface creerInstance(Controleur c, Stage primaryStage) {
        URL location = ParisOuvertsView.class.getResource("/views/jfx/ParisOuverts.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Pane root = null;
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ParisOuvertsView vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMaScene(new Scene(root, root.getPrefWidth(),root.getPrefHeight()));
        vue.setControleur(c);
        return vue;
    }


    @Override
    public void show() {
        primaryStage.setTitle("Paris Ouverts");
        primaryStage.setScene(getMaScene());
        primaryStage.show();
    }

    @Override
    public void majListeAllParis(Collection<Match> liste) {
        listeAllParis.getItems().setAll(liste);

        this.listeAllParis.setCellFactory(new Callback<ListView<Match>, ListCell<Match>>() {
            public ListCell<Match> call(ListView<Match> param) {
                ListCell<Match> cell = new ListCell<Match>(){
                    @Override
                    protected void updateItem(Match m, boolean bln) {
                        super.updateItem(m, bln);
                        if (m != null) {
                            setText(m.getSport()+ " : " + m.getEquipe1() + " VS " + m.getEquipe2()+ " le "+ m.getQuand());
                        }
                    }
                };
                return cell;
            }
        });


        listeAllParis.setOnMouseClicked(mouseEvent -> {
            Match match = listeAllParis.getSelectionModel().getSelectedItem();
            controleur.goToParier(match);
        });


    }

    public void goToMenuPrincipal(ActionEvent actionEvent) {
        controleur.goToMenuPrincipal();
    }


}
