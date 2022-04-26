package fr.snowsdy.controleAcces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fr/snowsdy/controleAcces/client/views/Connexion.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Image image = new Image("/resources/icons/server.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Contrôle d'Accès Ultimate");

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
