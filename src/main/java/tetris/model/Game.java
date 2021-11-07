package tetris.model;

import tetris.gui.ActionEvent;
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
     * Starts the game by creating a block and waiting for action events.
     */
    public void start() {
        createFigure();
        while (true) {
            ActionEvent event = gui.waitEvent();
            handleEvent(event);
        }
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
        switch (Figures.getRandomFigure()) {
            case IFigure -> {figure = new IFigure((width - 1) / 2, height - 1);}
            case JFigure -> {figure = new JFigure((width - 1) / 2, height - 1);}
            case LFigure -> {figure = new LFigure((width - 1) / 2, height - 1);}
            case OFigure -> {figure = new OFigure((width - 1) / 2, height - 1);}
            case SFigure -> {figure = new SFigure((width - 1) / 2, height - 1);}
            case TFigure -> {figure = new TFigure((width - 1) / 2, height - 1);}
            case ZFigure -> {figure = new ZFigure((width - 1) / 2, height - 1);}
        }
        updateGUI();
    }

    /**
     * Handles an action event by moving the block accordingly.
     *
     * @param event the event to handle
     */
    private void handleEvent(ActionEvent event) {
        switch (event) {
            case MOVE_DOWN -> figure.move(0, -1);
            case MOVE_LEFT -> figure.move(-1, 0);
            case MOVE_RIGHT -> figure.move(1, 0);
            case ROTATE_LEFT -> figure.rotate(1);
            case ROTATE_RIGHT -> figure.rotate(-1);
        }
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
