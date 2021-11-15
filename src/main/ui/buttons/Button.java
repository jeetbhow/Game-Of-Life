package ui.buttons;

import model.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Buttons check if they have been pressed and then perform
 * some kind of manipulation on the board.
 */
public abstract class Button extends JButton implements ActionListener {
    Board board;

    // EFFECTS: Instantiates a button.
    public Button(Board board) {
        this.board = board;
    }

    // Performs the function associated with the button.
    public abstract void activate();

    // EFFECTS: Checks if the button has been pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            activate();
        }
    }
}
