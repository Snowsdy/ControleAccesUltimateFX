package fr.snowsdy.controleAcces.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {

    @FXML
    private VBox btn_personne;

    @FXML
    private VBox btn_badge;

    @FXML
    private VBox btn_attribution;

    @FXML
    private VBox btn_evenement;

    @FXML
    private VBox btn_salle;

    @FXML
    private VBox btn_autorisation;

    @FXML
    private VBox btn_timeSlot;

    @FXML
    void pressed(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        if (btn_personne == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/PersonneView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } else if (btn_badge == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/BadgeView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }else if (btn_attribution == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/AttributionView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }else if (btn_evenement == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/EventView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }else if (btn_salle == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/SalleView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }else if (btn_autorisation == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/AutorisationView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }else if (btn_timeSlot == node){
            Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/TimeSlotView.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
