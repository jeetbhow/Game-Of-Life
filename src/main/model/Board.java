package model;

import java.util.ArrayList;
import java.util.Scanner;

import static model.State.*;

/* A board consists of a 2D array of cells. You can set the dimensions
 * of the board, and add and remove cells from it. You can also change
 * the state of a cell. */

public class Board {
    int height;
    int width;
    private ArrayList<ArrayList<Cell>> cells;

    /* EFFECTS: Instantiates a Board with the given width and height. */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>();
        createBoard();
    }

    /* MODIFIES: this
     * EFFECTS: Adds rows and columns to the Board based on the width and height. */
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

    /* EFFECTS: Takes two integers row and column as input
         and returns the Cell at the given index. */
    public Cell getCell(int row, int column) {
        return cells.get(row).get(column);
    }

    /* MODIFIES: this
     * EFFECTS: Increments width by one and adds a new
     * column of dead cells to the Board. */
    public void addColumn() {
        width++;
        for (ArrayList<Cell> index : cells) {
            index.add(new Cell());
        }
    }

    /* MODIFIES: this
     * EFFECTS: Increments rows by one and adds
     * a new row of dead cells to the Board. */
    public void addRow() {
        height++;
        ArrayList<Cell> listToAdd = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            listToAdd.add(new Cell());
        }
        cells.add(listToAdd);
    }

    /* REQUIRES: row and column must be non-negative.
     * MODIFIES: this
     * EFFECTS: Flips the state of a Cell on the Board. */
    public void flipCell(int row, int column) {
        cells.get(row).get(column).flip();
    }

    /* REQUIRES: row and column must be non-negative.
     * EFFECTS: Takes a location on the board as input (row and column)
     * and outputs an ArrayList<Cell> that contains all of the other cells
     * around the location of interest. */
    public ArrayList<Cell> getSurroundingCells(int row, int column) {
        ArrayList<Cell> output = new ArrayList<>();
        return output;
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

    /* MODIFIES: this
     * REQUIRES: height and width must correspond to the actual
     * height and width in of the string.
     * EFFECTS: Takes a string containing the toString output of a board
     * as input and transforms the board to match the string. */
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
