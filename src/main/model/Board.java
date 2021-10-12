package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/* A board consists of a 2D array of cells. You can set the dimensions
 * of the board, and add and remove cells from it. You can also change
 * the state of a cell. */

public class Board {
    int columns;
    int rows;
    private LinkedList<LinkedList<Cell>> cells;

    /* EFFECTS: Instantiates a board. */
    public Board(int columns, int rows) {

    }

    /* MODIFIES: this
     * EFFECTS: Increments columns by one. */
    public void addColumn() {

    }

    /* MODIFIES: this
     * EFFECTS: Increments rows by one. */
    public void addRow() {

    }


    /* REQUIRES: i and j must be non-negative.
     * MODIFIES: Cell.state.
     * EFFECTS: Flips the state of a cell on the board. A dead cell will flip to
     * alive, and an alive one will flip to dead. */
    public void flipCell(int i, int j) {

    }

    /* EFFECTS: Returns a string representation of the board. */
    public String toString() {
        return "";
    }
}
