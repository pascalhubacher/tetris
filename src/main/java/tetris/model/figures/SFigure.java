package tetris.model.figures;

import tetris.gui.Block;
import tetris.model.Figure;

public class SFigure extends Figure {

    private final int COLOR = 4;

    public SFigure(int x, int y) {
        super(x, y);
        blocks[0] = new Block(x - 1, y-1, COLOR);
        blocks[1] = new Block(x, y-1, COLOR);
        blocks[2] = new Block(x, y , COLOR);
        blocks[3] = new Block(x + 1, y , COLOR);
    }
}
