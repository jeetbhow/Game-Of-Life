package model;

import java.util.ArrayList;
import java.util.Scanner;

import static model.State.*;

/*
 * Represents a 2D array of cells. Contains a height and width.
 */

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
            this.cells.add(new ArrayList<>());
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
        cells = checkVerticalAndCorners(row, column, cells);
        if (column > 0) {
            cells.add(this.getCell(row, column - 1));
        }
        if (column < width - 1) {
            cells.add(this.getCell(row, column + 1));
        }
        return cells;
    }

    /* REQUIRES: row and column are non-negative. Cells is non-empty.
       MODIFIES: Cells
       EFFECTS: Checks the top and bottom cells as well as the corners.
     */
    private ArrayList<Cell> checkVerticalAndCorners(int row, int column, ArrayList<Cell> cells) {
        if (row > 0) {
            cells.add(this.getCell(row - 1, column));
            if (column > 0) {
                cells.add(this.getCell(row - 1, column - 1));
            }
            if (column < width - 1) {
                cells.add(this.getCell(row - 1, column + 1));
            }
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
     * REQUIRES: String contains a grid of "." and "*" characters separated by " | ", like what's
     * returned by toString(). Height and width are non-negative and correspond to the string.
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
            } else if (next.equals("O")) {
                tempArray.get(i).set(j, new Cell(ALIVE));
            }
            j++;
        }
        cells = tempArray;
        this.height = height;
        this.width = width;
    }
}
