package tetris.model;

import tetris.gui.Block;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Figure {

    /**
     * The blocks of the figure.
     */
    public final Block[] blocks = new Block[4];

    /**
     * The Color of the figure.
     */
    public final int COLOR = ThreadLocalRandom.current().nextInt(0, 6 + 1);

    /*
    public Figure(int x, int y) {
        // S
        blocks[0] = new Block(x - 1, y, COLOR);
        blocks[1] = new Block(x, y, COLOR);
        blocks[2] = new Block(x, y + 1, COLOR);
        blocks[3] = new Block(x + 1, y + 1, COLOR);
    }*/

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
            block.y += dy;
        }
    }

    /**
     * Rotates the figure 90 degrees.
     * Every figure is looked at as 3 block vectors around a certain block(block[1])
     * [x y] = [y -x] vector turned 90 degrees
     * turned around block 2 block[1]
     * the block vector is calculated like this:
     * x = (y - reference block y) + .x
     * y = reference block y - (x - reference block x)
     */
    public void rotate(int d) {
        switch (d) {
            case 1 -> {
                for (Block block : blocks) {
                    int tempBlock = blocks[1].y - (block.x - blocks[1].x);
                    block.x = (block.y - blocks[1].y) + blocks[1].x;
                    block.y = tempBlock;
                }
            }
            case -1 -> {
                // missing
            }
        }
    }


}
