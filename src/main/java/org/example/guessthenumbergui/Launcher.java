package org.example.guessthenumbergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class to start the JavaFX application.
 */
public class Launcher extends Application {
    /**
     * Triggers the launch of the JavaFX application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Launch the JavaFX application by creating and displaying
     * the main window.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * @throws IOException if the FXML file failed to load.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Guess The Number");
        stage.setScene(scene);
        stage.show();
    }
}
