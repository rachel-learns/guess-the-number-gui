package org.example.guessthenumbergui;

import org.example.guessthenumbergui.model.GameModel;
import org.example.guessthenumbergui.model.RoundModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelUnitTest {
    GameModel game = new GameModel();

    @Test
    public void startRoundTest() {
        RoundModel round = game.startRound();
        assertNotNull(round);
        assertEquals(1, game.getActualRound());
        assertEquals(2, game.getNbrRounds());
    }

    @Test
    public void finalMessageScoreTest() {
        game.setNbrRounds(0);
        String msgFinalScore = "Your final score is 0 in 3 rounds.";
        assertEquals(msgFinalScore, game.finalMessageScore());
    }

    @Test
    public void endGameTest() {
        assertFalse(game.endGame());

        game.setNbrRounds(0);
        assertTrue(game.endGame());
    }
}
