package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class IFigure extends Figure {
    public IFigure(int x, int y) {
        blocks[0] = new Block(x - 1, y, COLOR);
        blocks[1] = new Block(x, y, COLOR);
        blocks[2] = new Block(x + 1, y, COLOR);
        blocks[3] = new Block(x + 2, y, COLOR);
    }
}
