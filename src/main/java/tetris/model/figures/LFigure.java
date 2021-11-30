package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class LFigure extends Figure {

    private int[] rotateBlock = new int[2];

    private final int COLOR = 2;

    public LFigure(int x, int y) {
        super(x, y);
        blocks[0] = new Block(x - 1, y-1, COLOR);
        blocks[1] = new Block(x, y-1, COLOR);
        blocks[2] = new Block(x + 1, y-1, COLOR);
        blocks[3] = new Block(x + 1, y , COLOR);
        rotateBlock[0] = blocks[1].x;
        rotateBlock[1] = blocks[1].y + 1;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        rotateBlock[0] += dx;
        rotateBlock[1] += dy;
    }

    @Override
    public void rotate(int d) {
        switch (d) {
            case 1 -> {
                for (Block block : blocks) {
                    int tempBlock = rotateBlock[1] - (block.x - rotateBlock[0]);
                    block.x = (block.y - rotateBlock[1]) + rotateBlock[0];
                    block.y = tempBlock;
                }
                // rotate rotateBlock
                //tempBlock = rotateBlock[1] - (block.x - rotateBlock[0]);
            }
            case -1 -> {
                // missing
            }
        }
    }
}
