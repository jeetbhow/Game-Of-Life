package ui.buttons;

import model.Board;
import ui.Grid;

public class AddColumnButton extends Button {

    public AddColumnButton(Board board, Grid grid) {
        super(board, grid);
        setText("Add Column");
    }

    @Override
    public void activate() {
        grid.setUnitWidth(grid.getWidth() / board.getWidth());
        board.addColumn();
    }
}
