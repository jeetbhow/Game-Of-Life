package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
    The Board class contains an n x n array of cells.
 */
public class Board {
    private int size;
    private Cell[][] cells;

    public Board(int size) {
        cells = new Cell[size][size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /* EFFECTS: Returns a string representation of the board. As
     *          an example, if you have a 2x2 board of dead cells, then
     *          it will return
     *
     *           | . | . |
     *           | . | . |
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append("| ");
            for (int j = 0; j < size; j++) {
                string.append(cells[i][j]);
                if (j != size - 1) {
                    string.append(" | ");
                }
            }
            string.append(" |");
            if (i != size - 1) {
                string.append("\n");
            }
        }
        return string.toString();
    }

    // GETTERS
    public int getSize() {
        return size;
    }
}
