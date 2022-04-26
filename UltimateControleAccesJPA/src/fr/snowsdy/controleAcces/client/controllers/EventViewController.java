package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Evenement;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.physique.data.EvenementDataService;
import fr.snowsdy.controleAcces.physique.data.PhysiqueDataFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EventViewController implements Initializable {

    private List<Evenement> events;
    private final EvenementDataService evenementDataService = PhysiqueDataFactory.getEvenementDataService();
    ObservableList<Evenement> observableList = FXCollections.observableArrayList();
    double x = 0, y = 0;

    @FXML
    private TableView<Evenement> table;

    @FXML
    private TableColumn<Evenement, Long> col_id;

    @FXML
    private TableColumn<Evenement, Personne> col_pers;

    @FXML
    private TableColumn<Evenement, Long> col_salle;

    @FXML
    private TableColumn<Evenement, Date> col_date;

    @FXML
    private TableColumn<Evenement, String> col_autorise;

    @FXML
    void RemoveEvent(MouseEvent event) throws Exception {
        event.consume();
        evenementDataService.remove(table.getSelectionModel().getSelectedItem());
        observableList.removeAll(events);
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

    private String isAutorise(boolean value){
        return value ? "Oui" : "Non";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            events = evenementDataService.getAll();

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_pers.setCellValueFactory(new PropertyValueFactory<>("personne"));
            col_salle.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getSalle().getId()));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_autorise.setCellValueFactory(param -> new ReadOnlyStringWrapper(isAutorise(param.getValue().isAutorise())));

            observableList.addAll(events);

            table.setItems(observableList);
            table.setPlaceholder(new Label("Aucune donnée"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
