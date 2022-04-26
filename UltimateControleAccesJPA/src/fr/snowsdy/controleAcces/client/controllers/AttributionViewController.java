package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Attribution;
import fr.snowsdy.controleAcces.metier.entities.Badge;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.physique.data.AttributionDataService;
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

public class AttributionViewController implements Initializable {

    double x = 0, y = 0;

    private List<Attribution> attributions;
    private final AttributionDataService attributionDataService = PhysiqueDataFactory.getAttributionDataService();
    ObservableList<Attribution> attributionObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Attribution> table;

    @FXML
    private TableColumn<Attribution, Long> col_id;

    @FXML
    private TableColumn<Attribution, Badge> col_badge;

    @FXML
    private TableColumn<Attribution, Personne> col_personne;

    @FXML
    void RemoveAttr(MouseEvent event) throws Exception {
        event.consume();
        this.attributionDataService.remove(table.getSelectionModel().getSelectedItem());
        attributionObservableList.removeAll(attributions);
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
            attributions = attributionDataService.getAll();

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_badge.setCellValueFactory(new PropertyValueFactory<>("badge"));
            col_personne.setCellValueFactory(new PropertyValueFactory<>("personne"));
            attributionObservableList.addAll(attributions);

            table.setItems(attributionObservableList);
            table.setPlaceholder(new Label("Aucune donnée"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
