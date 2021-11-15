package ui;

import model.Board;
import ui.buttons.AddRowButton;
import ui.buttons.Button;

import javax.swing.*;
import java.util.ArrayList;

/*
 * Represents a panel of buttons.
 */
public class ButtonPanel extends JPanel {
    private ArrayList<Button> buttons;

    // EFFECTS: Instantiates a ButtonPanel.
    public ButtonPanel() {
        buttons = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a button to buttons.
    public void add(Button button) {
        buttons.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Removes a button from buttons.
    public void remove(Button button) {
        buttons.remove(button);
    }

    // MODIFIES: this
    // EFFECTS: Returns a button from the buttons
    public Button get(int index) {
        return buttons.get(index);
    }
}
