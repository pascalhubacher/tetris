package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class OFigure extends Figure {

    private final int COLOR = 3;

    public OFigure(int x, int y) {
        super(x, y);
        blocks[0] = new Block(x, y, COLOR);
        blocks[1] = new Block(x+1, y, COLOR);
        blocks[2] = new Block(x, y - 1, COLOR);
        blocks[3] = new Block(x+1, y - 1, COLOR);
    }

    @Override
    public void rotate(int d) {
        // do nothing
    }
}
