package ui.buttons;

import model.Board;
import ui.Grid;

// A button that adds a row onto the board.
public class AddRowButton extends Button {

    // EFFECTS: Instantiates an AddRow button.
    public AddRowButton(Board board, Grid grid) {
        super(board, grid);
        setText("Add Row");
    }

    // MODIFIES: Board.cells
    // EFFECTS: Adds a row to the Board.
    @Override
    public void activate() {
        grid.setUnitHeight(grid.getHeight() / board.getHeight());
        board.addRow();
    }
}
