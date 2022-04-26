package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Day;
import fr.snowsdy.controleAcces.metier.entities.TimeSlot;
import fr.snowsdy.controleAcces.physique.data.PhysiqueDataFactory;
import fr.snowsdy.controleAcces.physique.data.TimeSlotDataService;
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

public class TimeSlotViewController implements Initializable {

    double x = 0, y = 0;
    private List<TimeSlot> timeSlots;
    private final TimeSlotDataService timeSlotDataService = PhysiqueDataFactory.getTimeSlotService();
    ObservableList<TimeSlot> observableList = FXCollections.observableArrayList();

    @FXML
    private TableView<TimeSlot> table;

    @FXML
    private TableColumn<TimeSlot, Long> col_id;

    @FXML
    private TableColumn<TimeSlot, Day> col_JDebut;

    @FXML
    private TableColumn<TimeSlot, Integer> col_HDebut;

    @FXML
    private TableColumn<TimeSlot, Integer> col_MDebut;

    @FXML
    private TableColumn<TimeSlot, Day> col_JFin;

    @FXML
    private TableColumn<TimeSlot, Integer> col_HFin;

    @FXML
    private TableColumn<TimeSlot, Integer> col_MFin;

    @FXML
    void RemoveTs(MouseEvent event) throws Exception {
        event.consume();
        this.timeSlotDataService.remove(table.getSelectionModel().getSelectedItem());
        observableList.removeAll(timeSlots);
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
            timeSlots = timeSlotDataService.getAll();

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_JDebut.setCellValueFactory(new PropertyValueFactory<>("beginDay"));
            col_HDebut.setCellValueFactory(new PropertyValueFactory<>("beginHour"));
            col_MDebut.setCellValueFactory(new PropertyValueFactory<>("beginMinutes"));
            col_JFin.setCellValueFactory(new PropertyValueFactory<>("endDay"));
            col_HFin.setCellValueFactory(new PropertyValueFactory<>("endHour"));
            col_MFin.setCellValueFactory(new PropertyValueFactory<>("endMinutes"));

            observableList.addAll(timeSlots);

            table.setItems(observableList);
            table.setPlaceholder(new Label("Aucune donnée"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
