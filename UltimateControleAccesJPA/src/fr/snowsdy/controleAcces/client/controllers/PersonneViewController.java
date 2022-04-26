package fr.snowsdy.controleAcces.client.controllers;

import fr.snowsdy.controleAcces.client.PersonneB;
import fr.snowsdy.controleAcces.metier.entities.Administrateur;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.physique.data.PersonneDataService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PersonneViewController implements Initializable {

    double x = 0, y = 0;
    private List<PersonneB> personnesB;
    private List<Personne> personnes;
    private final PersonneDataService pSrv = PhysiqueDataFactory.getPersonneDataService();
    ObservableList<PersonneB> observableList = FXCollections.observableArrayList();

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;

    @FXML
    private CheckBox cb_admin;

    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_mdp;

    @FXML
    private TableView<PersonneB> table;

    @FXML
    private TableColumn<PersonneB, Long> col_id;

    @FXML
    private TableColumn<PersonneB, String> col_nom;

    @FXML
    private TableColumn<PersonneB, String> col_prenom;

    @FXML
    private TableColumn<PersonneB, Character> col_discriminant;

    @FXML
    private TableColumn<PersonneB, String> col_login;

    @FXML
    private TableColumn<PersonneB, String> col_mdp;

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
    void addEdit(Event event) {
        event.consume();
        boolean admin = false;
        if (cb_admin.isSelected())
            admin = true;
        boolean trouve = false;
        Personne p = new Personne();
        if (!tf_nom.getText().isEmpty() && !tf_prenom.getText().isEmpty()){
            p.setNom(tf_nom.getText());
            p.setPrenom(tf_prenom.getText());
        }
        for (Personne personne : personnes){
            if ((p.getNom().equals(personne.getNom())) && (p.getPrenom().equals(personne.getPrenom()))){
                // Correspondance trouvé
                trouve = true;
                p.setId(personne.getId());
            }
        }

        try {
            Administrateur a = new Administrateur();
            a.setId(p.getId());
            a.setPrenom(p.getPrenom());
            a.setNom(p.getNom());
            a.setLogin(tf_login.getText());
            a.setMdp(pf_mdp.getText());

            if (trouve) {
                if (admin){
                    this.pSrv.update(a);
                } else {
                    this.pSrv.update(p);
                }
            } else {
                if (admin) {
                    this.pSrv.add(a);
                } else {
                    this.pSrv.add(p);
                }
            }
            observableList.removeAll(personnesB);
            initialize(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void RemovePersonne(MouseEvent event) throws Exception {
        event.consume();
         PersonneB personneB = table.getSelectionModel().getSelectedItem();
        if (personneB.getDiscriminant() == 'A') {
            Administrateur a = new Administrateur();
            a.setId(personneB.getId());
            a.setNom(personneB.getNom());
            a.setPrenom(personneB.getPrenom());
            a.setLogin(personneB.getLogin());
            a.setMdp(personneB.getPassword());
            this.pSrv.remove(a);
        } else {
            Personne p = new Personne();
            p.setId(personneB.getId());
            p.setNom(personneB.getNom());
            p.setPrenom(personneB.getPrenom());
            this.pSrv.remove(p);
        }

        observableList.removeAll(personnesB);
        initialize(null, null);
    }

    private void transform(List<Personne> personnes) {
        for (Personne p : personnes){
            PersonneB pB = new PersonneB();

            pB.setId(p.getId());
            pB.setNom(p.getNom());
            pB.setPrenom(p.getPrenom());
            pB.setDiscriminant(p);
            pB.setLogin(p);
            pB.setPassword(p);

            this.personnesB.add(pB);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            personnesB = new ArrayList<>();
            personnes = PhysiqueDataFactory.getPersonneDataService().getAll();
            this.transform(personnes);

            this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            this.col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            this.col_discriminant.setCellValueFactory(new PropertyValueFactory<>("discriminant"));
            this.col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
            this.col_mdp.setCellValueFactory(new PropertyValueFactory<>("password"));

            observableList.addAll(personnesB);

            table.setItems(observableList);
            table.setPlaceholder(new Label("Aucune donnée"));

            this.cb_admin.setOnMousePressed(event -> {
                this.tf_login.setDisable(cb_admin.isSelected());
                this.pf_mdp.setDisable(cb_admin.isSelected());
            });
            this.tf_prenom.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    this.addEdit(event);
                }
            });
            this.pf_mdp.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    this.addEdit(event);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
