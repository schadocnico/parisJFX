package views.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import views.MenuPrincipalInterface;

import java.io.IOException;
import java.net.URL;

/**
 * Created by YohanBoichut on 10/11/15.
 */
public class MenuPrincipal implements MenuPrincipalInterface {


    Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @FXML
    VBox topNiveau;

    @FXML
    PasswordField pwd;

    @FXML
    TextField pseudo;

    @FXML
    private Button monBouton;


    private Stage primaryStage;


    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public PasswordField getPwd() {
        return pwd;
    }

    public void setPwd(PasswordField pwd) {
        this.pwd = pwd;
    }

    public TextField getPseudo() {
        return pseudo;
    }

    public void setPseudo(TextField pseudo) {
        this.pseudo = pseudo;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private TextField monChamp;

    public static MenuPrincipal creerInstance(Controleur c, Stage primaryStage) {
        URL location = MenuPrincipal.class.getResource("/views/jfx/menuPrincipal.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Pane root = null;
        try {
            root = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuPrincipal vue = fxmlLoader.getController();
        vue.setControleur(c);
        vue.setScene(new Scene(root, 300, 275));
        vue.setPrimaryStage(primaryStage);
        return vue;
    }


    public void show() {
        primaryStage.setTitle("Menu principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goToAccueil(ActionEvent actionEvent){
        String pseudo = getPseudo().getText();
        String pwd = getPwd().getText();
        System.out.println(pseudo);
        System.out.println(pwd);
        this.controleur.goToAccueil(pseudo,pwd);

    }


    public void quitter(ActionEvent actionEvent){
        primaryStage.close();
    }



}
