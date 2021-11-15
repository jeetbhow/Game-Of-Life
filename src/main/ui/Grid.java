package ui;

import model.Board;
import model.Cell;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

// Represents a grid of squares.
public class Grid extends JPanel {
    Board board;
    private int unitHeight;
    private int unitWidth;

    // EFFECTS: Instantiates a grid.
    public Grid(Board board) {
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<ArrayList<Cell>> cells = board.getCells();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Cell currCell = cells.get(i).get(j);
                if (currCell.getState() == State.ALIVE) {
                    g2d.setPaint(Color.GREEN);
                } else {
                    g2d.setPaint(Color.BLACK);
                }
                g2d.fillRect(j * unitWidth, i * unitHeight, unitWidth, unitHeight);
            }
        }
    }

    public int getUnitHeight() {
        return unitHeight;
    }

    public int getUnitWidth() {
        return unitWidth;
    }

    public void setUnitHeight(int unitHeight) {
        this.unitHeight = unitHeight;
    }

    public void setUnitWidth(int unitWidth) {
        this.unitWidth = unitWidth;
    }
}
