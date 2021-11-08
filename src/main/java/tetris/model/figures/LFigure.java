package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class LFigure extends Figure {

    public LFigure(int x, int y) {
        super(x, y);
        blocks[0] = new Block(x - 1, y, COLOR);
        blocks[1] = new Block(x, y, COLOR);
        blocks[2] = new Block(x + 1, y, COLOR);
        blocks[3] = new Block(x + 1, y + 1, COLOR);
    }

    @Override
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
