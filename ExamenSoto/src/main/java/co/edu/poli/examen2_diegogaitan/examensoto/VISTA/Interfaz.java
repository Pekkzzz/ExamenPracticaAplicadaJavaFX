package co.edu.poli.examen2_diegogaitan.examensoto.VISTA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Interfaz extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Interfaz.class.getResource("/co/edu/poli/examen2_diegogaitan/examensoto/hello-view.fxml")
        );

        TabPane root = fxmlLoader.load();

        scene = new Scene(root);
        stage.setTitle("Sistema de Gestión de Seguros - Politécnico Grancolombiano");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Interfaz.class.getResource("/co/edu/poli/examen2_diegogaitan/examensoto/" + fxml + ".fxml")
        );
        scene.setRoot(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}