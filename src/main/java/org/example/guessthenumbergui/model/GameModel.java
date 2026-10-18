package org.example.guessthenumbergui.model;

public class GameModel {
    private int nbrRounds = 2;
    private int finalScore = 0;
    private int actualRound = 0;

    public int getActualRound() { return actualRound; }

    public void setFinalScore(int value) { finalScore = finalScore + value; }

    public RoundModel startRound() {
        RoundModel round = new RoundModel();
        nbrRounds--;
        actualRound++;
        return round;
    }

    public String finalMessageScore() {
        nbrRounds = 2 - nbrRounds;
        String msgTextScore = String.format("Your final score is %d in %d game%s.", finalScore, nbrRounds, (nbrRounds > 1) ? "s" : "");
        return msgTextScore;
    }

    public boolean endGame() {
        return (nbrRounds == 0);
    }
}
