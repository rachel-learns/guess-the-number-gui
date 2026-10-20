package org.example.guessthenumbergui;

import org.example.guessthenumbergui.model.RoundModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoundModelUnitTest {
    RoundModel round = new RoundModel();

    @Test
    public void generateRandomNumberTest() {
        int number = round.generateRandomNumber(0, 100);
        assertTrue(number >= 0 && number <= 100);
    }

    @Test
    public void compareNumberTest() {
        round.setSolutionNumber(50);
        round.setNumber("49");
        assertEquals(-1, round.compareNumber());

        round.setNumber("51");
        assertEquals(1, round.compareNumber());

        round.setNumber("50");
        assertEquals(0, round.compareNumber());
    }

    @Test
    public void boundsHintTest() {
        round.setSolutionNumber(50);
        round.setNumber("25");
        round.boundsHint();
        assertEquals(25, round.getMin());

        round.setNumber("75");
        round.boundsHint();
        assertEquals(75, round.getMax());
    }

    @Test
    public void messageHintTest() {
        round.setSolutionNumber(50);
        round.setNumber("25");
        String msgHint1 = "It's more!\n";
        msgHint1 = msgHint1 + "Search between 25 and 100\n";
        msgHint1 = msgHint1 + "You have 4 attempts left.";
        assertEquals(msgHint1, round.messageHint());

        round.setNumber("75");
        String msgHint2 = "It's less!\n";
        msgHint2 = msgHint2 + "Search between 25 and 75\n";
        msgHint2 = msgHint2 + "You have 3 attempts left.";
        assertEquals(msgHint2, round.messageHint());

        round.setNumber("50");
        assertEquals("", round.messageHint());
    }

    @Test
    public void scoreRoundTest() {
        round.setNumberIsFound(true);
        round.setNbrLeftAttempts(4);
        int score = round.scoreRound();
        assertEquals(10, score);

        round.setNbrLeftAttempts(3);
        int score1 = round.scoreRound();
        assertEquals(4, score1);

        round.setNbrLeftAttempts(0);
        int score2 = round.scoreRound();
        assertEquals(1, score2);

        round.setNumberIsFound(false);
        round.setNbrLeftAttempts(0);
        int score3 = round.scoreRound();
        assertEquals(0, score3);
    }

    @Test
    public void messageScoreTest() {
        round.setNumberIsFound(true);
        round.setNbrLeftAttempts(4);
        String msgScore1 = "You won!\n";
        msgScore1 = msgScore1 + "Your score is 10 for this round.";
        assertEquals(msgScore1, round.messageScore());

        round.setNumberIsFound(false);
        round.setSolutionNumber(50);
        String msgScore2 = "You lost! The solution was : 50.\n";
        msgScore2 = msgScore2 + "Your score is 0 for this round.";
        assertEquals(msgScore2, round.messageScore());
    }

    @Test
    public void endRoundTest() {
        round.setNumberIsFound(false);
        round.setNbrLeftAttempts(4);
        assertFalse(round.endRound());

        round.setNumberIsFound(true);
        assertTrue(round.endRound());

        round.setNumberIsFound(false);
        round.setNbrLeftAttempts(0);
        assertTrue(round.endRound());

        round.setNumberIsFound(true);
        assertTrue(round.endRound());
    }
}
