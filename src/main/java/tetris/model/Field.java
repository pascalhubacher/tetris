package tetris.model;

import tetris.gui.Block;

public class Field {

    private final int height;
    private final int width;

    /**
     * The elements of the field.
     */
    protected final int[][] field;

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
        this.field = new int[height][width];
    }

    public int getWidth() {
        return width;

    }

    public int getHeight() {
        return height;
    }

    /**
     * prüft, ob Blöcke mit dem Spielfeldrand kollidieren und wenn ja,
     * eine CollisionException wirft.
     */
    public void detectCollision(Block[] blocks) throws CollisionException {
        for (Block block : blocks) {
            // Figure hit the ground
            if (block.y < 0) {
                throw new CollisionException("Figure hit the ground.");
            }
            // Figure hit the left border
            if (block.x < 0) {
                throw new CollisionException("Figure hit the left border.");
            }
            // Figure hit the right border
            if (block.x > getWidth() - 1) {
                throw new CollisionException("Figure hit the right border.");
            }
        }
    }
}
