package ui.buttons;

import model.Board;
import ui.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represents a button on the UI.
 */
public abstract class Button extends JButton implements ActionListener {
    Board board;
    Grid grid;

    // EFFECTS: Instantiates a button.
    public Button(Board board, Grid grid) {
        this.board = board;
        this.grid = grid;
        setFocusable(false);
        addActionListener(this);
    }

    // Performs the function associated with the button.
    public abstract void activate();

    // EFFECTS: Checks if the button has been pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            activate();
            grid.repaint();
            board.randomize();
        }
    }
}
