package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class TFigure extends Figure{

    private final int COLOR = 5;

    public TFigure(int x, int y) {
        super(x, y);
        blocks[0] = new Block(x-1, y-1, COLOR);
        blocks[1] = new Block(x, y-1, COLOR);
        blocks[2] = new Block(x + 1, y-1, COLOR);
        blocks[3] = new Block(x, y, COLOR);
    }
}
