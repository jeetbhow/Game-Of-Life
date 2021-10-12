package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static model.State.*;

/* A board consists of a 2D array of cells. You can set the dimensions
 * of the board, and add and remove cells from it. You can also change
 * the state of a cell. */

public class Board {
    int rows;
    int columns;
    private ArrayList<ArrayList<Cell>> cells;

    /* EFFECTS: Instantiates a board. */
    public Board(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.cells = new ArrayList<>();
        createBoard();
    }

    /* MODIFIES: this.cells
     * EFFECTS: Creates the actual multidimensional array that stores
     * all of the cell data. */
    private void createBoard() {
        for (int i = 0; i < rows; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                cells.get(i).add(new Cell());
            }
        }
    }

    /* MODIFIES: this
     * EFFECTS: Increments columns by one and adds a new cell at the
     * end of each list in the 2D array.*/
    public void addColumn() {
        columns++;
        for (ArrayList<Cell> index : cells) {
            index.add(new Cell());
        }
    }

    /* MODIFIES: this
     * EFFECTS: Increments rows by one and adds a new ArrayList to the 2D array. */
    public void addRow() {
        rows++;
        ArrayList<Cell> listToAdd = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            listToAdd.add(new Cell());
        }
        cells.add(listToAdd);
    }

    /* REQUIRES: i and j must be non-negative.
     * MODIFIES: Cell.state.
     * EFFECTS: Flips the state of a cell on the board. A dead cell will flip to
     * alive, and an alive one will flip to dead. */
    public void flipCell(int i, int j) {
        cells.get(i).get(j).flip();
    }

    public void stringToArray(String string, int rows, int columns) {
        ArrayList<ArrayList<Cell>> tempArray = cells;
        Scanner s = new Scanner(string);
        int i = 0;
        int j = 0;
        while (s.hasNext()) {
            if (j >= columns) {
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
        this.rows = rows;
        this.columns = columns;
    }

    /* EFFECTS: Returns a string representation of the board. */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            string.append("| ");
            for (int j = 0; j < columns; j++) {
                string.append(cells.get(i).get(j));
                string.append((j != columns - 1) ? " | " : " |");
            }
            string.append((i != rows - 1) ? "\n" : "");
        }
        return string.toString();
    }

    public ArrayList<ArrayList<Cell>> getCells() {
        return cells;
    }
}
