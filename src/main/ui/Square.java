package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

// Represents a cell in the Game of Life.
public class Square extends JPanel {
    private Color color;

    // EFFECTS: Instantiates a square with the given color.
    public Square(Color color) {
        this.color = color;
    }

    // EFFECTS: Paints a square onto the window.
    public void paint(Graphics graphics) {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
