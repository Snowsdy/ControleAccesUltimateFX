package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Autorisation;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.metier.entities.Salle;
import fr.snowsdy.controleAcces.metier.entities.TimeSlot;
import fr.snowsdy.controleAcces.physique.data.AutorisationDataService;
import fr.snowsdy.controleAcces.physique.data.PhysiqueDataFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AutorisationViewController implements Initializable {

    private List<Autorisation> autorisations;
    private final AutorisationDataService autorisationDataService = PhysiqueDataFactory.getAutorisationDataService();
    ObservableList<Autorisation> observableList = FXCollections.observableArrayList();

    double x = 0, y = 0;

    @FXML
    private TableView<Autorisation> table;

    @FXML
    private TableColumn<Autorisation, Long> col_id;

    @FXML
    private TableColumn<Autorisation, Salle> col_salle;

    @FXML
    private TableColumn<Autorisation, Personne> col_personne;

    @FXML
    private TableColumn<Autorisation, TimeSlot> col_timeSlot;

    @FXML
    void RemoveAuto(MouseEvent event) throws Exception {
        event.consume();
        this.autorisationDataService.remove(table.getSelectionModel().getSelectedItem());
        observableList.removeAll(autorisations);
        initialize(null, null);
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            autorisations = autorisationDataService.getAll();

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            col_personne.setCellValueFactory(new PropertyValueFactory<>("personne"));
            col_timeSlot.setCellValueFactory(new PropertyValueFactory<>("plageHoraire"));

            observableList.addAll(autorisations);

            table.setItems(observableList);
            table.setPlaceholder(new Label("Aucune donnée"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
