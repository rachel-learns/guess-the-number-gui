package org.example.guessthenumbergui.model;

public class GameModel {
    private int nbrRounds = 3;
    private int finalScore = 0;
    private int actualRound = 0;

    public int getActualRound() { return actualRound; }

    public void setFinalScore(int value) { finalScore = finalScore + value; }

    public int getNbrRounds() { return nbrRounds; }
    public void setNbrRounds(int value) { nbrRounds = value; }

    /**
     * Creates a round.
     *
     * @return the actual round.
     */
    public RoundModel startRound() {
        RoundModel round = new RoundModel();
        nbrRounds--;
        actualRound++;
        return round;
    }

    /**
     * Builds the final score message.
     *
     * @return the final score message.
     */
    public String finalMessageScore() {
        nbrRounds = 3 - nbrRounds;
        String msgTextScore = String.format("Your final score is %d in %d round%s.", finalScore, nbrRounds, (nbrRounds > 1) ? "s" : "");
        return msgTextScore;
    }

    /**
     * Checks if the game is finished.
     *
     * @return true is the game is finished, false otherwise.
     */
    public boolean endGame() {
        return (nbrRounds == 0);
    }
}
