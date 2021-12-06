package tetris.model;

import java.io.*;
import java.util.Scanner;

public class Scoring {
    private static final String HIGH_SCORE_FILE_PATH = "./highScore.txt";
    private static final String ENCODING = "UTF-8";
    private int score;
    private int level;
    private int highScore;

    Scoring() {
        this.score = 0;
        this.level = 0;
        this.loadHighScore();
    }

    void updateScore(int value) {
        score += value;
    }

    void updateLevel() {
        level += 1;
    }

    void updateHighScore(int value) {
        highScore += value;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public void reset(){
        this.score = 0;
        this.level = 0;
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
            }
        } else {
            //set highScore to 0
            highScore = 0;
        }
    }

    public void saveHighScore() {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(HIGH_SCORE_FILE_PATH), ENCODING));
            writer.println(Integer.toString(score));
            writer.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
