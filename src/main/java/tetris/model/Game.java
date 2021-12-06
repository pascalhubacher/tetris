package tetris.model;

import tetris.gui.ActionHandler;
import tetris.gui.Block;
import tetris.gui.GUI;
import tetris.model.figures.*;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.*;

/**
 * The class Game implements the Tetris game.
 *
 * @author Pascal Hubacher
 * @version 2.1
 */
public class Game {

    /**
     * The width of the field.
     */
    private final int width;

    /**
     * The height of the field.
     */
    private final int height;

    /**
     * The graphical user interface.
     */
    private final GUI gui;

    /**
     * The figure of the game.
     */
    private Figure figure;

    /**
     * The scoreing of the game.
     */
    private Scoring scoring;
    private int lines;

    /**
     * The field of the game.
     */
    private Field field;

    private static final Logger LOGGER = Logger.getLogger("Game.class");

    /**
     * Constructs a game with the specified graphical user interface.
     *
     * @param width  the width of the field
     * @param height the height of the field
     * @param gui    the graphical user interface
     */
    public Game(int width, int height, GUI gui) {
        this.width = width;
        this.height = height;
        this.gui = gui;
        field = new Field(height, width);
        this.scoring = new Scoring();
        this.lines = 0;
    }


    /**
     * Handles an action event by moving the block accordingly.
     **/
    private class FigureController implements ActionHandler {
        /**
         * Moves the figure down
         */
        @Override
        public void moveDown() {
            try {
                figure.move(0, -1);
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException e) {
                figure.move(0, 1);
                figureLanded();
            }
        }

        /**
         * Moves the figure left
         */
        @Override
        public void moveLeft() {
            figure.move(-1, 0);
            try {
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException e) {
                figure.move(1, 0);
            }
        }

        /**
         * Moves the figure right
         */
        @Override
        public void moveRight() {
            figure.move(1, 0);
            try {
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException e) {
                figure.move(-1, 0);
            }
        }

        /**
         * Rotates the figure
         */
        @Override
        public void rotateLeft() {
            figure.rotate(1);
            try {
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException e) {
                figure.rotate(-1);
            }
        }

        /**
         * Rotates the figure
         */
        @Override
        public void rotateRight() {
            figure.rotate(-1);
            try {
                field.detectCollision(figure.getBlocks());
                updateGUI();
            } catch (CollisionException e) {
                figure.rotate(1);
            }
        }

        /**
         * deletes the figure
         */
        @Override
        public void drop() {
            try {
                // move 1 by 1 down until the CollisionException is thrown
                for (int i = 1; i <= height; i++) {
                    figure.move(0, -1);
                    field.detectCollision(figure.getBlocks());
                }
            } catch (CollisionException e) {
                figure.move(0, 1);
            }
            updateGUI();
        }
    }

    /**
     * Starts the game by creating a block and waiting for action events.
     */
    public void start() {
        createFigure();
        FigureController figureController = new FigureController();
        gui.setActionHandler(figureController);
        updateGUI();
    }

    public void figureLanded() {
        field.addBlocks(figure.getBlocks());

        //Das Entfernen von 1, 2, 3 oder 4 vollen Zeilen ergibt 40, 100, 300 bzw. 1200 Punkte.
        switch(field.removeFullRows()) {
            case 1:
                lines += 1;
                scoring.updateScore(40);
                break;
            case 2:
                lines += 2;
                scoring.updateScore(100);
                break;
            case 3:
                lines += 3;
                scoring.updateScore(300);
                break;
            case 4:
                lines += 4;
                scoring.updateScore(1200);
                break;
        }
        //Nach dem Entfernen von jeweils 10 vollen Zeilen, wird die Spielstufe erhöht und die Figuren fallen schneller.
        if (lines >= 10) {
            scoring.updateLevel();
            lines -= 10;
        }
        createFigure();
    }

    private void stop() {
        LOGGER.log(Level.INFO, "stop() method");
        gui.setActionHandler(null);
        figure = null;
        // save highscore
        if (scoring.getScore() > scoring.getHighScore()) {
           scoring.saveHighScore();
        }
    }

    /**
     * Enum of all possible figures.
     */
    private enum Figures {
        IFigure, JFigure, LFigure, OFigure, SFigure, TFigure, ZFigure;

        /**
         * Pick a random value of the BaseColor enum.
         *
         * @return a random Figure.
         */
        public static Figures getRandomFigure() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    /**
     * Creates a figure on a random basis
     */
    public void createFigure() {
        // create a random figure
        figure = switch (Figures.getRandomFigure()) {
            case IFigure -> new IFigure((width - 1) / 2, height - 1);
            case JFigure -> new JFigure((width - 1) / 2, height - 1);
            case LFigure -> new LFigure((width - 1) / 2, height - 1);
            case OFigure -> new OFigure((width - 1) / 2, height - 1);
            case SFigure -> new SFigure((width - 1) / 2, height - 1);
            case TFigure -> new TFigure((width - 1) / 2, height - 1);
            case ZFigure -> new ZFigure((width - 1) / 2, height - 1);
        };
        figure = new IFigure((width - 1) / 2, height - 1);

        try {
            field.detectCollision(figure.getBlocks());
        } catch (CollisionException ex) {
            stop();
        }
        updateGUI();

    }

    /**
     * Updates the graphical user interface according to the current state of the game.
     */
    private void updateGUI() {
        gui.clear();
        gui.drawBlocks(field.getBlocks());
        if (figure != null) {
            gui.drawBlocks(figure.getBlocks());
        }
        gui.setLevel(scoring.getLevel());
        gui.setScore(scoring.getScore());
        gui.setHighScore(scoring.getHighScore());
    }
}
