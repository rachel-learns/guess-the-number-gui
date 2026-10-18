package org.example.guessthenumbergui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class HelloController {
    @FXML
    private Button startButton;

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
