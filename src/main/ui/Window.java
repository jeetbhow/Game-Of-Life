package ui;

import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;

import static ui.GameOfLife.*;

// Represents a Window.
public class Window extends JFrame {

    // EFFECTS: Instantiates a Window.
    public Window(int width, int height) {
        setTitle("Game Of Life");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    }
}
