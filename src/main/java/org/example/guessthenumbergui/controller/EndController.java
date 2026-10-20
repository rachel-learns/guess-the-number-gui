package org.example.guessthenumbergui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.guessthenumbergui.model.GameModel;

/**
 * This controller class makes the connection between the FXML view
 * (end-view.fxml) and the business logic.
 */
public class EndController {
    private GameModel game;

    @FXML
    private Button btnRestart;

    @FXML
    private Text textFinalScore;

    /**
     * Retrieves the current game from the GameController and updates
     * the final score text.
     *
     * @param game the current game.
     */
    public void setGame(GameModel game) {
        this.game = game;
        textFinalScore.setText(game.finalMessageScore());
    }

    /**
     * Handles the click of the restart button.
     *
     * Method called automatically by the framework via the attribute
     * onAction="#onRestartBtnClick" declared in the FXML.
     * She loads the FXML game view (game-view.fxml) and set the new scene.
     */
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

    /**
     * Handles the click of the quit button.
     *
     * Method called automatically by the framework via the attribute
     * onAction="#onQuitBtnClick" declared in the FXML.
     * She closes the application.
     */
    @FXML
    private void onQuitBtnClick() {
        Platform.exit();
    }
}
