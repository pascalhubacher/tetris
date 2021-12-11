package tetris.model;

import tetris.gui.Block;

import java.util.Arrays;
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
        this.blocks.addAll(Arrays.asList(blocks));
    }

    public void removeAllBlocks() {
        blocks.clear();
    }

    public boolean isRowFull(int row) {
        int rowElementCount = 0;
        for (Block blockInField : this.blocks) {
            if (blockInField.y == row) {
                rowElementCount += 1;
            }
        }
        return (rowElementCount == getWidth());
    }

    public void removeRow(int row) {
        /*
        // iterator
        Iterator<Block> iter = blocks.iterator();
        while (iter.hasNext()){
            Block b = iter.next();
            if (b.y == row) {
                iter.remove(); //geht nur mit einem iterator
            } else if (b.y > row) {
                b.y--;
            }
        }

        // lambda expression
        blocks.removeIf(block -> block.y == row);
        blocks.forEach(block -> {
            if (block.y > row) block.y--;
        });

        */

        // create new list
        Set<Block> newBlocksInField = new HashSet<>();
        for (Block blockInField : this.blocks) {
            if (blockInField.y > row) {
                //move every block y-1 down
                blockInField.y -= 1;
                newBlocksInField.add(blockInField);
            }
            if (blockInField.y < row) {
                newBlocksInField.add(blockInField);
            }
        }
        this.removeAllBlocks();
        // add all new blocks
        this.blocks.addAll(newBlocksInField);
    }

    public int removeFullRows() {
        int nrows = 0;
        for (int i = getHeight() - 1; i >= 0; i--) {
            if (isRowFull(i)) {
                //remove row
                removeRow(i);
                nrows++;
            }
        }
        return nrows;
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

