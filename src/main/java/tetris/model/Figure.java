package tetris.model;

import tetris.gui.Block;

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
    private final Block[] blocks = new Block[4];

    /**
     * The Color of the figure.
     */
    private static final int COLOR = ThreadLocalRandom.current().nextInt(0, 6 + 1);

    public Figure(int x, int y) {
        this.x = x;
        y -= 3;
        this.y = y;
        // I
        blocks[0] = new Block(x - 1, y, COLOR);
        blocks[1] = new Block(x, y, COLOR);
        blocks[2] = new Block(x + 1, y, COLOR);
        blocks[3] = new Block(x + 2, y, COLOR);
        // S
        blocks[0] = new Block(x - 1, y, COLOR);
        blocks[1] = new Block(x, y, COLOR);
        blocks[2] = new Block(x, y + 1, COLOR);
        blocks[3] = new Block(x + 1, y + 1, COLOR);
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
        for (Block block : blocks) {
            block.x += dx;
            block.x += dy;
        }
    }

    /**
     * Rotates the figure.
     */
    public void rotate(int d) {
        for (Block block : blocks) {
            // [x y] = [y -x] vector turned 90 degrees
            // turned around block 2 block[1]
            int tempBlock = blocks[1].y - (block.x - blocks[1].x);
            block.x = (block.y - blocks[1].y)+blocks[1].x;
            block.y = tempBlock;

        }

    }


}
