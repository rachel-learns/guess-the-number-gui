package org.example.guessthenumbergui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.guessthenumbergui.model.GameModel;
import org.example.guessthenumbergui.model.RoundModel;

public class GameController {
    private final GameModel game = new GameModel();
    private RoundModel round = new RoundModel();

    @FXML
    private Text textNbrRound;

    @FXML
    private TextField textUserInput;

    @FXML
    private Text textHint;

    @FXML
    private Text textScore;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnFinalScore;

    @FXML
    private void initialize() {
        game.startRound();
        textUserInput.textProperty().bindBidirectional(round.numberProperty());
    }

    @FXML
    private void onConfirmBtnClick() {
        // We retrieve the user input
        round.setNumber(textUserInput.getText());
        // We update the hint text
        textHint.setText(round.messageHint());
        // We clear the user input field
        textUserInput.clear();

        if(game.endGame() && round.endRound()) {
            btnFinalScore.setVisible(true);
            btnFinalScore.setManaged(true);
            textScore.setText(round.messageScore());
            game.setFinalScore(round.getScore());
        } else if(round.endRound()) {
            textScore.setText(round.messageScore());

            game.setFinalScore(round.getScore());

            // Make the buttons visible
            btnStop.setVisible(true);
            btnStop.setManaged(true);
            btnNext.setVisible(true);
            btnNext.setManaged(true);
        }
    }

    @FXML
    private void handleNext() {
        round = game.startRound();

        textNbrRound.setText("Round " + game.getActualRound());

        textHint.setText("");
        textScore.setText("");

        // Hiding the buttons
        btnStop.setVisible(false);
        btnStop.setManaged(false);
        btnNext.setVisible(false);
        btnNext.setManaged(false);
    }

    @FXML
    private void handleStop() {
        try {
            FXMLLoader endView = new FXMLLoader(getClass().getResource("/org/example/guessthenumbergui/end-view.fxml"));
            Parent root = endView.load();

            EndController controller = endView.getController();
            controller.setGame(game);

            btnStop.getScene().setRoot(root);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
