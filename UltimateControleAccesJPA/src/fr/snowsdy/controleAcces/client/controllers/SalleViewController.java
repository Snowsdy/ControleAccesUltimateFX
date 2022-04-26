package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Salle;
import fr.snowsdy.controleAcces.physique.data.PhysiqueDataFactory;
import fr.snowsdy.controleAcces.physique.data.SalleDataService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SalleViewController implements Initializable {

    double x = 0, y = 0;
    private List<Salle> salles;
    private final SalleDataService service = PhysiqueDataFactory.getSalleDataService();
    ObservableList<Salle> observableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Salle> table;

    @FXML
    private TableColumn<Salle, Long> col_id;

    @FXML
    private TableColumn<Salle, String> col_protege;

    @FXML
    private TextField tf_id;

    @FXML
    private CheckBox cb_protege;

    @FXML
    void addEditSalle(Event event) throws Exception {
        event.consume();
        long salle = Long.parseLong(tf_id.getText());
        boolean protege = cb_protege.isSelected();
        Salle s = new Salle();
        s.setId(salle);
        s.setProtege(protege);
        for (Salle sal : salles){
            if (sal.getId() == s.getId()){
                // Correspondance trouvé : update
                this.service.update(s);
            } else {
                this.service.add(s);
            }
        }

        observableList.removeAll(salles);
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

    @FXML
    void removeSalle(Event event) throws Exception {
        event.consume();
        Salle s = table.getSelectionModel().getSelectedItem();
        this.service.remove(s);

        observableList.removeAll(salles);
        initialize(null, null);
    }

    private String isProtege(boolean value) {
        if (value)
            return "Protégé";
        else
            return "N'est pas Protégé";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            salles = service.getAll();

            this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.col_protege.setCellValueFactory(param -> new ReadOnlyStringWrapper(isProtege(param.getValue().isProtege())));

            observableList.addAll(salles);

            table.setItems(observableList);
            table.setPlaceholder(new Label("Aucune donnée"));

            this.cb_protege.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        this.addEditSalle(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            this.tf_id.setOnKeyPressed(event -> {
                if (tf_id.getText().length() == 3) {
                    tf_id.setText(tf_id.getText(0,2));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
