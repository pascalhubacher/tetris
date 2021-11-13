package tetris.model;

import tetris.gui.Block;

public class Field {

    /**
     * The elements of the field.
     */
    protected final int[][] field;

    public Field(int height, int width) {
        this.field = new int[height][width];
    }

    public void getWith() {

    }

    public void getHeight() {

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
            if (block.x > field[0].length - 1) {
                throw new CollisionException("Figure hit the right border.");
            }
        }

    }

}
