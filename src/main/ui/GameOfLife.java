package ui;

import model.Board;
import ui.buttons.AddColumnButton;
import ui.buttons.AddRowButton;
import ui.buttons.RunSimulationButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameOfLife {
    public static final int SCREEN_WIDTH = 750;
    public static final int SCREEN_HEIGHT = 750;


    private Board board;
    private Window window;
    private ButtonPanel buttonPanel;
    private Grid grid;

    // EFFECTS: Instantiates the GameOfLife.
    public GameOfLife() {
        board =  new Board(1,1);
        window = new Window(SCREEN_WIDTH, SCREEN_HEIGHT);
        buttonPanel = new ButtonPanel();
        grid = new Grid(board);
        initialize();
    }

    public void initialize() {
        //Buttons
        buttonPanel.add(new AddRowButton(board, grid));
        buttonPanel.add(new AddColumnButton(board, grid));
        buttonPanel.add(new RunSimulationButton(board, grid));
        window.add(buttonPanel, BorderLayout.SOUTH);
        //Grid
        grid.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (e.getComponent() == grid) {
                    grid.setUnitWidth(grid.getWidth() / board.getWidth());
                    grid.setUnitHeight(grid.getHeight() / board.getHeight());
                    board.randomize();
                }
            }
        });
        window.add(grid);
    }
}
