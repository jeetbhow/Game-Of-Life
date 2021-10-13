package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static model.State.*;

/* A Board consists of a 2D array of cells. You can set the dimensions
 * of the board, and add and remove cells from it. You can also change
 * the state of a cell. */

public class Board {
    private int height;
    private int width;
    private ArrayList<ArrayList<Cell>> cells;


    /* REQUIRES: Width and height are non-negative.
       EFFECTS: Instantiates a Board with the given width and height. */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>();
        createBoard();
    }

    /* EFFECTS: Instantiates a clone of another board. */
    public Board(Board original) {
        this.height = original.getHeight();
        this.width = original.getWidth();
        this.cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            this.cells.add(new ArrayList<Cell>());
            for (int j = 0; j < this.width; j++) {
                this.cells.get(i).add(new Cell(original.getCell(i,j)));
            }
        }
    }

    /* MODIFIES: This
     * EFFECTS: Adds rows and columns to the Board. */
    private void createBoard() {
        for (int i = 0; i < height; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                cells.get(i).add(new Cell());
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<Cell>> cells) {
        this.cells = cells;
    }

    /* REQUIRES: Row and column are non-negative.
       EFFECTS: Returns the Cell at the given index. */
    public Cell getCell(int row, int column) {
        return cells.get(row).get(column);
    }

    /* MODIFIES: This
     * EFFECTS: Adds a new column of dead cells to the board. */
    public void addColumn() {
        width++;
        for (ArrayList<Cell> index : cells) {
            index.add(new Cell());
        }
    }

    /* MODIFIES: This
     * EFFECTS: Adds a new row of dead cells to the Board. */
    public void addRow() {
        height++;
        ArrayList<Cell> listToAdd = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            listToAdd.add(new Cell());
        }
        cells.add(listToAdd);
    }

    /* REQUIRES: Row and column must be non-negative.
     * MODIFIES: This
     * EFFECTS: Flips the state of a Cell on the Board. */
    public void flipCell(int row, int column) {
        cells.get(row).get(column).flip();
    }

    /* REQUIRES: Row and column are non-negative.
     * EFFECTS: Returns a list of all cells around a point. */
    public ArrayList<Cell> scan(int row, int column) {
        ArrayList<Cell> cells = new ArrayList<>();
        if (row > 0) {
            cells.add(this.getCell(row - 1, column));
            if (column > 0) {
                cells.add(this.getCell(row - 1, column - 1));
            }
            if (column < width - 1) {
                cells.add(this.getCell(row - 1, column + 1));
            }
        }
        if (column > 0) {
            cells.add(this.getCell(row, column - 1));
        }
        if (row < height - 1) {
            cells.add(this.getCell(row + 1, column));
            if (column > 0) {
                cells.add(this.getCell(row + 1, column - 1));
            }
            if (column < width - 1) {
                cells.add(this.getCell(row + 1, column + 1));
            }
        }
        if (column < width - 1) {
            cells.add(this.getCell(row, column + 1));
        }
        return cells;
    }

    // EFFECTS: Updates the board and returns it.
    public Board update() {
        Board nextGeneration = new Board(this);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ArrayList<Cell> surroundingCells = this.scan(i, j);
                Cell curr = nextGeneration.getCell(i,j);
                curr.interact(surroundingCells);
            }
        }
        this.cells = nextGeneration.getCells();
        return nextGeneration;
    }

    /* MODIFIES: This
       EFFECTS: Sets the board to a random configuration. */
    public void randomize() {
        for (ArrayList<Cell> index : cells) {
            for (Cell cell : index) {
                cell.randomize();
            }
        }
    }

    /* EFFECTS: Returns a string representation of the board. */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < height; i++) {
            string.append("| ");
            for (int j = 0; j < width; j++) {
                string.append(cells.get(i).get(j));
                string.append((j != width - 1) ? " | " : " |");
            }
            string.append((i != height - 1) ? "\n" : "");
        }
        return string.toString();
    }

    /* MODIFIES: This
     * REQUIRES: String must contain a grid of "." and "*" characters separated by " | ", like what's
     * returned by toString(). Height and width are non-negative and correspond to this grid.
     * EFFECTS: Updates the board to the configuration given in a string. */
    public void stringToBoard(String string, int height, int width) {
        ArrayList<ArrayList<Cell>> tempArray = cells;
        Scanner s = new Scanner(string);
        int i = 0;
        int j = 0;
        while (s.hasNext()) {
            if (j >= width) {
                j = 0;
                i++;
            }
            String next = s.next();
            while (next.equals("|") && s.hasNext()) {
                next = s.next();
            }
            if (next.equals(".")) {
                tempArray.get(i).set(j, new Cell(DEAD));
            } else if (next.equals("*")) {
                tempArray.get(i).set(j, new Cell(ALIVE));
            }
            j++;
        }
        cells = tempArray;
        this.height = height;
        this.width = width;
    }
}
