package tetris.model;

import tetris.gui.Block;

import java.util.LinkedList;
import java.util.List;

public class Field {

    private final int height;
    private final int width;

    // hashSet oder hashMap
    private List<Block> blocksInField  = new LinkedList<>();;

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Block> getBlocks() {
        return blocksInField;
    }

    public void addBlocks(Block[] blocks) {
        for (Block block : blocks) {
            blocksInField.add(block);
        }
    }

    public void removeAllBlocks() {
        blocksInField.clear();
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
            if (block.x >= getWidth()) {
                throw new CollisionException("Figure hit the right border.");
            }
            // check if it collides with an existing block
            for (Block blockInField : blocksInField) {
                if (blockInField.x == block.x && blockInField.y == block.y) {
                    throw new CollisionException("Figure hit other figures.");
                }
            }
        }
    }
}

