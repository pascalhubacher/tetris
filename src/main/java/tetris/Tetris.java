package tetris;

import tetris.gui.GUI;
import tetris.model.Game;

/**
 * The class Tetris implements the main class to start a Tetris game.
 *
 * @author Pascal Hubacher
 * @version 2.1
 */
public class Tetris {

	/**
	 * The default width of the field.
	 */
	private static final int WIDTH = 10;

	/**
	 * The default height of the field.
	 */
	private static final int HEIGHT = 20;

	/**
	 * Creates a Tetris game using a graphical user interface and starts the game.
	 *
	 * @param args the width and height of the field
	 */
	public static void main(String[] args) {
		int width, height;
		try {
			width = Integer.parseInt(args[0]);
			height = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
			width = WIDTH;
			height = HEIGHT;
		}
		GUI gui = new GUI(width, height);
		Game game = new Game(width, height, gui);
		game.start();
	}
}
