package fr.snowsdy.controleAcces.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleBarController implements Initializable {

    @FXML
    void close(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void maximise(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    void reduce(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(!stage.isIconified());
    }

    @FXML
    void setFullScreen(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
