package org.example.guessthenumbergui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.guessthenumbergui.model.GameModel;
import org.example.guessthenumbergui.model.RoundModel;

/**
 * This controller class makes the connection between the FXML view
 * (game-view.fxml) and the business logic.
 */
public class GameController {
    private final GameModel game = new GameModel();
    private RoundModel round = new RoundModel();

    @FXML
    private Text textNbrRound;

    @FXML
    private TextField textUserInput;

    @FXML
    private Text textHint;

    /** Display the score only at the end of a round. */
    @FXML
    private Text textScore;

    /** Hidden until the end of a round. */
    @FXML
    private Button btnStop;

    /** Hidden until the end of a round. */
    @FXML
    private Button btnNext;

    /** Hidden until all the round are played. */
    @FXML
    private Button btnFinalScore;

    /**
     * Inits the controller after the FXML has been uploaded.
     *
     * Starts a round and bind the field textUserInput to the property
     * number in the RoundModel class.
     */
    @FXML
    private void initialize() {
        game.startRound();
        textUserInput.textProperty().bindBidirectional(round.numberProperty());
    }

    /**
     * Handles the click of the confirm button.
     *
     * Method called automatically by the framework via the attribute
     * onAction="#onConfirmBtnClick" declared in the FXML.
     * She updates the score and hint texts, and manages the visibility
     * of the stop, next and final score buttons.
     */
    @FXML
    private void onConfirmBtnClick() {
        round.setNumber(textUserInput.getText());
        textHint.setText(round.messageHint());
        textUserInput.clear();

        if(game.endGame() && round.endRound()) {
            textScore.setText(round.messageScore());
            game.setFinalScore(round.getScore());

            btnFinalScore.setVisible(true);
            btnFinalScore.setManaged(true);
        } else if(round.endRound()) {
            textScore.setText(round.messageScore());
            game.setFinalScore(round.getScore());

            btnStop.setVisible(true);
            btnStop.setManaged(true);
            btnNext.setVisible(true);
            btnNext.setManaged(true);
        }
    }

    /**
     * Handles the click of the next button.
     *
     * Method called automatically by the framework via the attribute
     * onAction="#onNextBtnClick" declared in the FXML.
     * She updates the hint, score and number round texts,
     * and manages the visibility of the stop and next buttons.
     */
    @FXML
    private void onNextBtnClick() {
        round = game.startRound();

        textNbrRound.setText("Round " + game.getActualRound());
        textHint.setText("");
        textScore.setText("");

        btnStop.setVisible(false);
        btnStop.setManaged(false);
        btnNext.setVisible(false);
        btnNext.setManaged(false);
    }

    /**
     * Handles the click of the stop button.
     *
     * Method called automatically by the framework via the attribute
     * onAction="#onStopBtnClick" declared in the FXML.
     * She loads the FXML end view (end-view.fxml) and set the new scene.
     */
    @FXML
    private void onStopBtnClick() {
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
