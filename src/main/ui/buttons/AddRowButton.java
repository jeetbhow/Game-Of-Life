package ui.buttons;

import model.Board;

// A button that adds a row onto the board.
public class AddRowButton extends Button {

    // EFFECTS: Instantiates an AddRow button.
    public AddRowButton(Board board) {
        super(board);
        setText("Add Row");
    }

    // MODIFIES: Board.cells
    // EFFECTS: Adds a row to the Board.
    @Override
    public void activate() {

    }
}
