package ui;

import model.Board;

public class GameOfLife {
    public static final int SCREEN_WIDTH = 750;
    public static final int SCREEN_HEIGHT = 750;

    private Board board;
    private Window window;
    private ButtonPanel buttonPanel;

    // EFFECTS: Instantiates the GameOfLife.
    public GameOfLife() {
        board =  new Board(1,1);
        window = new Window(SCREEN_WIDTH, SCREEN_HEIGHT);
        buttonPanel = new ButtonPanel();
    }


}
