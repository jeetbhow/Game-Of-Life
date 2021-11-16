package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;

// Acts as a container for all of the UI components.
public class UIPanel extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 150;

    // EFFECTS: Instantiates a UIPanel.
    public UIPanel(Board board, SimulationPanel sp) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
