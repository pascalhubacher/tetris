package tetris.model;

import tetris.gui.Block;

public abstract class Figure {

    /**
     * The blocks of the figure.
     */
    protected final Block[] blocks = new Block[4];

    public Figure(int x, int y) {

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
            block.y += dy;
        }
    }

    /**
     * Rotates the figure 90 degrees.
     * Every figure is looked at as 3 block vectors around a certain block(block[1])
     * [x y] = [y -x] vector turned 90 degrees cw
     * turned around block 2 block[1]
     * the block vector is calculated like this:
     * x = (y - reference block y) + .x
     * y = reference block y - (x - reference block x)
     */
    public void rotate(int d) {
        switch (d) {
            case 1 -> {
                // [x y] = [y -x] vector turned 90 degrees cw
                for (Block block : blocks) {
                    int tempBlock = blocks[1].y - (block.x - blocks[1].x);
                    block.x = (block.y - blocks[1].y) + blocks[1].x;
                    block.y = tempBlock;
                }
            }
            case -1 -> {
                //[x y] = [-y x] vector turned 90 degrees ccw
                for (Block block : blocks) {
                    int tempBlock = blocks[1].y + (block.x - blocks[1].x);
                    block.x = blocks[1].x - (block.y - blocks[1].y);
                    block.y = tempBlock;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + d);
        }
    }


}
