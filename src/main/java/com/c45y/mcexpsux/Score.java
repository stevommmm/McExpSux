package com.c45y.mcexpsux;

/**
 *
 * @author c45y
 */
public class Score {

    public final int max = 90000;
    private int score;

    private void setScore(int score) {
        this.score = score;
        if (this.score > this.max) {
            this.score = this.max;
        }
    }

    public int incrementScore(ScoreType st) {
        if(this.score != this.max) {
            int newScore = this.score + st.getPoints();
            this.setScore(newScore);
        }
        return this.getScore();
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return (int) Math.floor(this.getScore() / 1000);
    }

    public Score() {
        this.score = 0;
    }

    @Override
    public String toString() {
        return "" + score;
    }
}
