package tetris.model;

import tetris.gui.Block;

import java.util.HashSet;
import java.util.Set;

public class Field {

    private final int height;
    private final int width;

    // hashSet oder hashMap
    private final Set<Block> blocks = new HashSet<>();

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

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void addBlocks(Block[] blocks) {
        for (Block block : blocks) {
            this.blocks.add(block);
        }
    }

    public void removeAllBlocks() {
        blocks.clear();
    }

    public boolean isRowFull(int row) {
        int rowElementCount = 0;
        for (Block blockInField : blocks) {
            if (blockInField.y == row) {
                rowElementCount += 1;
            }
        }
        if (rowElementCount == getWidth()) {
            return true;
        } else {
            return false;
        }
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
            for (Block blockInField : this.blocks) {
                if (blockInField.x == block.x && blockInField.y == block.y) {
                    throw new CollisionException("Figure hit other figures.");
                }
            }
        }
    }
}

