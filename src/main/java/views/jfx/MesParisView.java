package views.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.Match;
import modele.Pari;
import views.MesParisInterface;
import views.ParisOuvertsInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class MesParisView implements MesParisInterface {

    @FXML
    VBox topNiveau;

    @FXML
    ListView listeMesParis;

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


    public static MesParisView creerInstance(Controleur c, Stage primaryStage) {
        URL location = MesParisView.class.getResource("/views/jfx/MesParis.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Pane root = null;
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MesParisView vue = fxmlLoader.getController();
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
    public void majListeMesParis(Collection<Pari> liste) {
        listeMesParis.getItems().setAll(liste);

        this.listeMesParis.setCellFactory(new Callback<ListView<Pari>, ListCell<Pari>>() {
            public ListCell<Pari> call(ListView<Pari> param) {
                ListCell<Pari> cell = new ListCell<Pari>(){
                    @Override
                    protected void updateItem(Pari m, boolean bln) {
                        super.updateItem(m, bln);
                        if (m != null) {
                           // if (m.getGain().isPresent()) {
                                setText(m.getMatch().getSport() + " : " + m.getMatch().getEquipe1() + " VS " + m.getMatch().getEquipe2() + " le " + m.getMatch().getQuand() + " mise de " + m.getMontant() + " pour " + m.getVainqueur());
                            //}
                            //else {
                              //  setText(m.getMatch().getSport() + " : " + m.getMatch().getEquipe1() + " VS " + m.getMatch().getEquipe2() + " le " + m.getMatch().getQuand() + " pour " + m.getVainqueur());
                            //}
                        }
                    }
                };
                return cell;
            }
        });


        listeMesParis.setOnMouseClicked(mouseEvent -> {
            Pari pari = (Pari) listeMesParis.getSelectionModel().getSelectedItem();
            System.out.println(pari);
            controleur.goToAnnuler(pari);
        });




    }

    public void goToMenuPrincipal(ActionEvent actionEvent) {
        controleur.goToMenuPrincipal();
    }
}
