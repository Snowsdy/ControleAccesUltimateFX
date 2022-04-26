package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.metier.entities.Badge;
import fr.snowsdy.controleAcces.physique.data.BadgeDataService;
import fr.snowsdy.controleAcces.physique.data.PhysiqueDataFactory;
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

public class BadgeViewController implements Initializable {

    double x = 0, y = 0;
    private List<Badge> badges;
    private final BadgeDataService badgeDataService = PhysiqueDataFactory.getBadgeDataService();
    ObservableList<Badge> observableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Badge> table;

    @FXML
    private TableColumn<Badge, Long> col_id;

    @FXML
    private TableColumn<Badge, String> col_contenu;

    @FXML
    private TextField tf_contenu;

    @FXML
    void addBadge(Event event) throws Exception {
        event.consume();
        String contenu = tf_contenu.getText();
        Badge b = new Badge();
        b.setContenu(contenu);
        this.badgeDataService.add(b);
        observableList.removeAll(badges);
        initialize(null, null);
    }

    @FXML
    void removeBadge(Event event) throws Exception {
        event.consume();
        Badge b = table.getSelectionModel().getSelectedItem();
        this.badgeDataService.remove(b);
        observableList.removeAll(badges);
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
            badges = badgeDataService.getAll();

            this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));

            observableList.addAll(badges);

            table.setItems(observableList);
            table.setPlaceholder(new Label("Aucune donnée"));

            this.tf_contenu.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        this.addBadge(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            this.tf_contenu.setOnKeyPressed(event -> {
                if (tf_contenu.getText().length() == 10) {
                    tf_contenu.setText(tf_contenu.getText(0, 9));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
