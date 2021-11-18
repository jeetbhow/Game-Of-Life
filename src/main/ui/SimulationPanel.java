package ui;

import model.Board;

import javax.swing.JPanel;
import java.awt.*;

import static model.State.ALIVE;

/*
    Represents a grid of pixels that are rendered onto the frame.
 */
public class SimulationPanel extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private int unitSizeWidth;
    private int unitSizeHeight;
    private Board board;


    //EFFECTS: Instantiates a SimulationPanel.
    public SimulationPanel(Board board) {
        this.board = board;
        initialize();
        calculateAndSetUnitSize();
    }

    // MODIFIES: this
    // EFFECTS: Changes the current board to given one.
    public void setBoard(Board board) {
        this.board = board;
    }

    // MODIFIES: this
    // EFFECTS: Sets the unit size based on the dimensions of the board.
    public void calculateAndSetUnitSize() {
        this.unitSizeHeight = HEIGHT / board.getHeight();
        this.unitSizeWidth = WIDTH / board.getWidth();
    }

    //EFFECTS: Initializes JPanel parameters.
    public void initialize() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    //EFFECTS: Prints a grid of squares onto the panel representing the state of the board.
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCell(i,j).getState() == ALIVE) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * unitSizeWidth, i * unitSizeHeight, unitSizeWidth, unitSizeHeight);
            }
        }
    }
}
