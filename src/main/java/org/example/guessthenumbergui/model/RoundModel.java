package org.example.guessthenumbergui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;


public class RoundModel {
    private final StringProperty number = new SimpleStringProperty();

    private int solutionNumber = generateRandomNumber(0, 100);
    private int score = 0;
    private boolean numberIsFound = false;
    private int nbrLeftAttempts = 5;
    private int min = 0;
    private int max = 100;

    public StringProperty numberProperty() { return number; }
    public String getNumber() { return number.get(); }
    public void setNumber(String value) { number.set(value); }

    public void setSolutionNumber(int value) { solutionNumber = value; }

    public int getScore() { return score; }
    public void setScore(int value) { score = value; }

    public void setNumberIsFound(boolean value) { numberIsFound = value; }

    public void setNbrLeftAttempts(int value) { nbrLeftAttempts = value; }

    public int getMin() { return min; }

    public int getMax() { return max; }

    /**
     * Generates a random number between a minimum bound and a maximum
     * bound.
     *
     * @param min the minimum bound.
     * @param max the maximum bound.
     * @return the generated number.
     */
    public int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }

    /**
     * Compares two numbers.
     *
     * @return a number indicating if number is equal, smaller or bigger
     * than solutionNumber.
     *
     */
    public int compareNumber() {
        return Integer.compare(Integer.parseInt(getNumber()), solutionNumber);
    }

    /**
     * Updates min and max as user enters numbers.
     */
    public void boundsHint() {
        int userNumber = Integer.parseInt(getNumber());
        if(userNumber <= solutionNumber && userNumber > min) {
            min = userNumber;
        }
        if(userNumber >= solutionNumber && userNumber < max) {
            max = userNumber;
        }
    }

    /**
     * Builds the hint message by comparing the user inputs and the
     * solution number.
     *
     * @return the hint message.
     */
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

    /**
     * Calculates the score of the round based on the number of
     * left attempts.
     *
     * @return the score of the round.
     */
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

    /**
     * Builds the score message and updates the score.
     *
     * @return the score message.
     */
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

    /**
     * Checks if the round is finished.
     *
     * @return true if the round is finished, false otherwise.
     */
    public boolean endRound() {
        return (numberIsFound || nbrLeftAttempts == 0);
    }
}
