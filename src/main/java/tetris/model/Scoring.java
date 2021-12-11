package tetris.model;

import java.io.*;
import java.util.Scanner;

public class Scoring {
    private static final String HIGH_SCORE_FILE_PATH = "./highScore.txt";
    private static final String ENCODING = "UTF-8";
    /**
     * The total number of removed rows.
     */
    private int removedRows = 0;

    /**
    * Rewards
    */
    private static final int[] SCORE_REWARDS = {0, 40, 100, 300, 1200};
    /**
    * Rows per level
    */
    private static final int ROWS_PER_LEVEL = 10;

    private int score;
    private int highScore;

    Scoring() {
        this.score = 0;
        this.loadHighScore();
    }

    void updateScore(int rows) {
        score += SCORE_REWARDS[rows];
        removedRows += rows;
    }

    void updateHighScore() {
        if (getScore() > getHighScore()) {
            saveHighScore();
        }
    }

    public int getHighScore() {
        return highScore;
    }

    public int getLevel() {
        return 1 + removedRows / ROWS_PER_LEVEL;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        this.score = 0;
        this.loadHighScore();
    }

    private void loadHighScore() {
        // file exists or create one
        File file = new File(HIGH_SCORE_FILE_PATH);
        if (file.exists() && !file.isDirectory()) {
            try (Scanner scanner = new Scanner(new File(HIGH_SCORE_FILE_PATH), ENCODING)) {
                while (scanner.hasNextLine())
                    highScore = Integer.parseInt(scanner.nextLine());
            } catch (FileNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("High score could not be read.");
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            //set highScore to 0
            highScore = 0;
        }
    }

    private void saveHighScore() {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(HIGH_SCORE_FILE_PATH), ENCODING))){
            writer.println(score);
        } catch (IOException e) {
            System.err.println("highscore could not be written.");
            System.err.println("Error: " + e.getMessage());
        }

    }
}
