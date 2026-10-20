package org.example.guessthenumbergui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * This controller class makes the connection between the FXML view
 * (hello-view.fxml) and the business logic.
 */
public class HelloController {

    @FXML
    private Button startButton;

    /**
     * Handles the click on the start button.
     *
     * Method called automatically by the framework via the attribute
     * onAction="#onStartBtnClick" declared in the FXML.
     * She loads the FXML game view (game-view.fxml) and set the new scene.
     */
    @FXML
    private void onStartBtnClick() {
        try {
            FXMLLoader gameView = new FXMLLoader(getClass().getResource("/org/example/guessthenumbergui/game-view.fxml"));
            Parent root = gameView.load();
            startButton.getScene().setRoot(root);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
