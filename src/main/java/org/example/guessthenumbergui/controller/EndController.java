package org.example.guessthenumbergui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.guessthenumbergui.model.GameModel;

public class EndController {
    private GameModel game;

    @FXML
    private Button btnRestart;

    @FXML
    private Text textFinalScore;

    public void setGame(GameModel game) {
        this.game = game;
        textFinalScore.setText(game.finalMessageScore());
    }

    @FXML
    private void onRestartBtnClick() {
        try {
            FXMLLoader gameView = new FXMLLoader(getClass().getResource("/org/example/guessthenumbergui/game-view.fxml"));
            Parent root = gameView.load();
            btnRestart.getScene().setRoot(root);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void onQuitBtnClick() {
        Platform.exit();
    }
}
