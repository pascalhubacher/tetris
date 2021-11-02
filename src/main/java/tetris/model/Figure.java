package tetris.model;

import tetris.gui.Block;
import tetris.gui.GUI;

import java.util.Random;

import java.util.concurrent.ThreadLocalRandom;

public class Figure {
    /**
     * The x value of the field.
     */
    private final int x;

    /**
     * The y value of the field.
     */
    private final int y;

    /**
     * The blocks of the figure.
     */
    public final Block[] blocks;

    /**
     * The Color of the figure.
     */
    private static final int COLOR = ThreadLocalRandom.current().nextInt(0, 6 + 1);

    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
        this.blocks = new Block[]{
                new Block(x - 1, y, COLOR),
                new Block(x, y, COLOR),
                new Block(x + 1, y, COLOR),
                new Block(x + 2, y, COLOR)
        };
    }

    /**
     * Gets the blocks of the figure.
     */

    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * Moves the figure in the specified direction.
     */
    public void move(int dx, int dy) {
    }

    /**
     * Rotates the figure.
     */
    public void rotate(int d) {
    }


}
