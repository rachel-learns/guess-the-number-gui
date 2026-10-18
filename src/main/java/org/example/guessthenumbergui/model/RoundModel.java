package org.example.guessthenumbergui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

public class RoundModel {
    private final StringProperty number = new SimpleStringProperty();
    private final int solutionNumber = generateRandomNumber(0, 100);

    private int score = 0;
    private boolean numberIsFound = false;
    private int nbrLeftAttempts = 5;
    private int min = 0;
    private int max = 100;

    public StringProperty numberProperty() { return number; }
    public String getNumber() { return number.get(); }
    public void setNumber(String value) { number.set(value); }

    public int getScore() { return score; }
    public void setScore(int value) { score = value; }

    public int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }

    public int compareNumber() {
        return Integer.compare(Integer.parseInt(getNumber()), solutionNumber);
    }

    public void boundsHint() {
        int userNumber = Integer.parseInt(getNumber());
        if(userNumber <= solutionNumber && userNumber > min) {
            min = userNumber;
        }
        if(userNumber >= solutionNumber && userNumber < max) {
            max = userNumber;
        }
    }

    public String messageHint() {
        String msgHint = "";
        nbrLeftAttempts--;

        int hint = compareNumber();
        boundsHint();

        if(hint == 0) {
            numberIsFound = true;
        } else {
            if(hint < 0) {
                msgHint = "It's more!\n";
            } else {
                msgHint = "It's less!\n";
            }
            msgHint = msgHint + String.format("Search between %d and %d\n", min, max);
            msgHint = msgHint + String.format("You have %d attempt%s left.", nbrLeftAttempts, (nbrLeftAttempts > 1) ? "s" : "");
        }

        return msgHint;
    }

    public int scoreRound() {
        int score;

        if(!numberIsFound) { return 0; }

        int nbrAttempts = 5 - nbrLeftAttempts;
        if(nbrAttempts == 1) {
            score = 10;
        } else {
            score = 6 - nbrAttempts;
        }

        return score;
    }

    // Change the score message at the end of the round
    public String messageScore() {
        String msgScore;

        if(numberIsFound) {
            msgScore = "You won!\n";
        } else {
            msgScore = "You lost! The solution was : " + solutionNumber + ".\n";
        }

        setScore(scoreRound());
        msgScore = msgScore + "Your score is " + score + " for this round.";
        return msgScore;
    }

    // Return true if the round is finished
    public boolean endRound() {
        return (numberIsFound || nbrLeftAttempts == 0);
    }
}
