package model;

import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;

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

    /*
     * REQUIRES: width and height are non-negative.
     * EFFECTS: Instantiates a Board with the given height, width, and contents.
     */
    public Board(int width, int height, ArrayList<ArrayList<Cell>> cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
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

    /* MODIFIES: this
     * EFFECTS: Adds rows and columns to the Board that was instantiated. */
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

    /* MODIFIES: this
     * EFFECTS: Adds a column of dead cells to the end of board. */
    public void addColumn() {
        for (ArrayList<Cell> list : cells) {
            list.add(new Cell());
        }
        width++;
        EventLog.getInstance().logEvent(new Event("Cells added to Board."));
    }

    /* MODIFIES: this
     * EFFECTS: Adds a row of dead cells to the end of the Board. */
    public void addRow() {
        ArrayList<Cell> listToAdd = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            listToAdd.add(new Cell());
        }
        cells.add(listToAdd);
        height++;
        EventLog.getInstance().logEvent(new Event("Cells added to Board."));
    }

    /*
     * REQUIRES: width > 0
     * MODIFIES: this
     * EFFECTS: Removes a column of cells from the end of the board.
     */
    public void removeColumn() {
        for (ArrayList<Cell> list : cells) {
            Cell cellToRemove = list.get(width - 1);
            list.remove(cellToRemove);
        }
        width--;
        EventLog.getInstance().logEvent(new Event("Cells removed from Board."));
    }

    /*
     * REQUIRES: height > 0
     * MODIFIES: this
     * EFFECTS: Removes a row of cells from the end of the board.
     */
    public void removeRow() {
        cells.remove(cells.get(height - 1));
        height--;
        EventLog.getInstance().logEvent(new Event("Cells removed from Board."));
    }

    /*
     * REQUIRES: row and column are non-negative.
     * MODIFIES: this
     * EFFECTS: Flips the state of a Cell on the Board at row and column.
     */
    public void flipCell(int row, int column) {
        cells.get(row).get(column).flip();
    }

    /*
     * REQUIRES: Row and column are non-negative.
     * EFFECTS: Returns a list of all cells around a point on the board.
     */
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

    /*
     * REQUIRES: row and column are non-negative.
     * MODIFIES: this
     * EFFECTS: Checks the top and bottom cells as well as the corners.
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

    /*
     * MODIFIES: this.
     * EFFECTS: Updates the board and returns it.
     */
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
        EventLog.getInstance().logEvent(new Event("Board updated."));
        return nextGeneration;
    }

    /* MODIFIES: this
     * EFFECTS: Sets the board to a random configuration.
     */
    public void randomize() {
        for (ArrayList<Cell> index : cells) {
            for (Cell cell : index) {
                cell.randomize();
            }
        }
        EventLog.getInstance().logEvent(new Event("Board randomized."));
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

    /*
     * MODIFIES: this
     * REQUIRES: string contains a grid of "." and "*" characters separated by " | ", like what's
     * returned by toString(). Height and width are non-negative and correspond to the string.
     * EFFECTS: Updates the board to the configuration given in string.
     */
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

    /*
     * MODIFIES: JSONObject.map
     * EFFECTS: Returns a JSON object containing the height, width, and states of the cells
     * on the board.
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("height", this.height);
        json.put("width", this.width);
        json.put("states", this.cells);
        EventLog.getInstance().logEvent(new Event("Board saved."));
        return json;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sets the board to a square board of the given size.
     */
    public void setSize(int size) {
        if (height < size) {
            for (int i = 0; i < size - height; i++) {
                addRow();
            }
        } else {
            for (int i = 0; i < height - size; i++) {
                removeRow();
            }
        }
        if (width < size) {
            for (int i = 0; i < size - width; i++) {
                addColumn();
            }
        } else {
            for (int i = 0; i < width - size; i++) {
                removeColumn();
            }
        }
        EventLog.getInstance().logEvent(new Event("Board size changed."));
    }

    /*
     * EFFECTS: Returns true if the object being compared to is of the same
     * type and returns the same string when toString is called. Returns
     * false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Board board = (Board) o;
            return this.toString().equals(board.toString());
        }
    }
}
