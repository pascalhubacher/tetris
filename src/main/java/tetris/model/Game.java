package tetris.model;

import tetris.gui.ActionEvent;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;
import tetris.model.figures.*;

import java.util.Random;

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
            figure.move(0, -1);
            updateGUI();
        }
        /**
         * Moves the figure left
         */
        @Override
        public void moveLeft() {
            figure.move(-1, 0);
            updateGUI();
        }
        /**
         * Moves the figure right
         */
        @Override
        public void moveRight() {
            figure.move(1, 0);
            updateGUI();
        }
        /**
         * Rotates the figure
         */
        @Override
        public void rotateLeft() {
            figure.rotate(1);
            updateGUI();
        }
        /**
         * Rotates the figure
         */
        @Override
        public void rotateRight() {
            figure.rotate(-1);
            updateGUI();
        }
        /**
         * deletes the figure
         */
        @Override
        public void drop() {
            updateGUI();
        }
    }

    /**
     * Starts the game by creating a block and waiting for action events.
     */
    public void start() {
        createFigure();
        gui.setActionHandler(new FigureController());
    }

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

    public void createFigure() {
        // create a random figure
        //figure = new Figure((width - 1) / 2, height - 1);
        figure = switch (Figures.getRandomFigure()) {
            case IFigure -> new IFigure((width - 1) / 2, height - 1);
            case JFigure -> new JFigure((width - 1) / 2, height - 1);
            case LFigure -> new LFigure((width - 1) / 2, height - 1);
            case OFigure -> new OFigure((width - 1) / 2, height - 1);
            case SFigure -> new SFigure((width - 1) / 2, height - 1);
            case TFigure -> new TFigure((width - 1) / 2, height - 1);
            case ZFigure -> new ZFigure((width - 1) / 2, height - 1);
        };
        //figure = new JFigure((width - 1) / 2, height - 1);
        updateGUI();
    }

    /**
     * Updates the graphical user interface according to the current state of the game.
     */
    private void updateGUI() {
        gui.clear();
        gui.drawBlocks(figure.getBlocks());
    }
}
