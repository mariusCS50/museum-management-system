package org.example.information;

public class Review {
    private int score;
    private String message;

    public Review(int score, String message) {
        this.score = score;
        this.message = message;
    }

    public int getScore() {
        return score;
    }

    public String getMessage() {
        return message;
    }
}
