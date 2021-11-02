package tetris.model;

import tetris.gui.ActionEvent;
import tetris.gui.Block;
import tetris.gui.GUI;

/**
 * The class Game implements the Tetris game.
 *
 * @author Stephan Fischli
 * @version 2.0
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
	 * The block of the game.
	 */
	private Block block;

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
		createBlock();
		while (true) {
			ActionEvent event = gui.waitEvent();
			handleEvent(event);
		}
	}

	/**
	 * Creates a block at the top of the field.
	 */
	private void createBlock() {
		block = new Block((width - 1) / 2, height - 1, 1);
		updateGUI();
	}

	/**
	 * Handles an action event by moving the block accordingly.
	 *
	 * @param event the event to handle
	 */
	private void handleEvent(ActionEvent event) {
		switch (event) {
			case MOVE_DOWN -> block.y--;
			case MOVE_LEFT -> block.x--;
			case MOVE_RIGHT -> block.x++;
		}
		updateGUI();
	}

	/**
	 * Updates the graphical user interface according to the current state of the game.
	 */
	private void updateGUI() {
		gui.clear();
		gui.drawBlock(block);
	}
}
