package tetris.model;

import tetris.gui.ActionHandler;
import tetris.gui.GUI;
import tetris.model.figures.*;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * The scoring of the game.
     */
    private final Scoring scoring;

    /**
     * The field of the game.
     */
    private final Field field;

    /**
     * Thread to move down the figure
     */
    private FigureController thread;

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
        updateGUI();
    }


    /**
     * Handles an action event by moving the block accordingly.
     **/
    private class FigureController extends Thread implements ActionHandler {
        /**
         * Moves the figure automatically down
         */
        @Override
        public void run() {
            while (true) {
                try {
                    try {
                        Thread.sleep(1100 - (scoring.getLevel() * 100L));
                    } catch (InterruptedException e) {
                        break;
                    }
                    figure.move(0, -1);
                    field.detectCollision(figure.getBlocks());
                    updateGUI();
                } catch (CollisionException e) {
                    figure.move(0, 1);
                    figureLanded();
                }
            }
        }

        /**
         * Moves the figure down
         */
        @Override
        public synchronized void moveDown() {
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
        public synchronized void moveLeft() {
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
        public synchronized void moveRight() {
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
        public synchronized void rotateLeft() {
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
        public synchronized void rotateRight() {
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
        public synchronized void drop() {
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
        thread = new FigureController();
        thread.start();
    }

    public void figureLanded() {
        field.addBlocks(figure.getBlocks());
        scoring.updateScore(field.removeFullRows());
        createFigure();
    }

    private void stop() {
        gui.setActionHandler(null);
        figure = null;
        // save highscore if needed
        scoring.updateHighScore();
        thread.interrupt();
        LOGGER.log(Level.INFO, "stop() method");
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
