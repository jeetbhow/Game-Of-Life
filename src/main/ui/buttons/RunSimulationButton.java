package ui.buttons;

import model.Board;
import ui.Grid;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RunSimulationButton extends Button {

    // EFFECTS: Instantiates an AddRow button.
    public RunSimulationButton(Board board, Grid grid) {
        super(board, grid);
        setText("Run Simulation");
    }

    // MODIFIES: Board.cells
    // EFFECTS: Adds a row to the Board.
    @Override
    public void activate() {
        Timer timer = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.update();
                grid.repaint();
            }
        });
        timer.start();
    }
}
