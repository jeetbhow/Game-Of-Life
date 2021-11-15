package ui.buttons;

import model.Board;
import ui.Grid;

public class SetSizeButton extends Button {

    // EFFECTS: Instantiates an AddRow button.
    public SetSizeButton(Board board, Grid grid) {
        super(board, grid);
        setText("Set Size");
    }

    // MODIFIES: Board.cells
    // EFFECTS: Adds a row to the Board.
    @Override
    public void activate() {
        grid.setUnitHeight(grid.getHeight() / board.getHeight());
        grid.setUnitWidth(grid.getUnitWidth() / board.getWidth());
    }
}
