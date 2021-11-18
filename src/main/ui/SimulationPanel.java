package ui;

import model.Board;

import javax.swing.JPanel;
import java.awt.*;

import static model.State.ALIVE;

/*
 * Represents a grid of pixels that are rendered onto the frame.
 */
public class SimulationPanel extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private int pixelWidth;
    private int pixelHeight;
    private Board board;


    /*
     * EFFECTS: Instantiates a SimulationPanel.
     */
    public SimulationPanel(Board board) {
        this.board = board;
        initializeDimensions();
        calculateAndSetUnitSize();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Changes the current board to the given one.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /*
     * MODIFIES this
     * EFFECTS: Sets the width and height of the pixels based on the
     * dimensions of the board.
     */
    public void calculateAndSetUnitSize() {
        this.pixelHeight = HEIGHT / board.getHeight();
        this.pixelWidth = WIDTH / board.getWidth();
    }


    /*
     * MODIFIES: this
     * EFFECTS: Sets the preferred dimensions of the JPanel.
     */
    public void initializeDimensions() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /*
     * MODIFIES: this
     * EFFECTS: Paints a grid of pixels onto the panel representing the current
     * state of the board.
     */
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
                g.fillRect(j * pixelWidth, i * pixelHeight, pixelWidth, pixelHeight);
            }
        }
    }
}
