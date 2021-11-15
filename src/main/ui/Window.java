package ui;

import javax.swing.*;



// Represents a window that contains the UI.
public class Window extends JFrame {

    // Instantiates a window with the given width and height.
    public Window(int width, int height) {
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
    }

}
