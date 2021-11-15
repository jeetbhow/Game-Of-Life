package ui.buttons;

import model.Board;

// A button that adds a row onto the board.
public class AddRow extends Button {

    // EFFECTS: Instantiates an AddRow button.
    public AddRow(Board board) {
        super(board);
    }

    // MODIFIES: Board.cells
    // EFFECTS: Adds a row to the Board.
    @Override
    public void activate() {

    }
}
