package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Administrateur;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.physique.data.PhysiqueDataFactory;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {

    List<Personne> personnes;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    double x = 0, y = 0;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void login(Event event) throws Exception {
        String username = tf_username.getText();
        String password = pf_password.getText();
        for (Personne p : personnes) {
            if (p instanceof Administrateur) {
                Administrateur a = (Administrateur) p;
                if ( (username.equals(a.getLogin()) && a.isValid(password)) ) {
                    // L'identification est confirmé : passer à la page d'accueil
                    Parent root = FXMLLoader.load(getClass()
                            .getResource("/fr/snowsdy/controleAcces/client/views/Accueil.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.centerOnScreen();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            pf_password.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)){
                    try {
                        login(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            personnes = PhysiqueDataFactory.getPersonneDataService().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
