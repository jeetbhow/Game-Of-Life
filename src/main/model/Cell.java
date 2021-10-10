package model;

/* Class: Cell
 * The basic unit of the Game of Life. Cells contain a state
 * of either DEAD or ALIVE. They also store their own position
 * on the board. They can read the state of the surrounding cells
 * on the board and use that information to update their state.
 */
public class Cell {
    private State state;


    /*
     * EFFECT: Initializes a Cell.
     */
    public Cell(State state) {
        this.state = state;
    }

    /*
     * EFFECT: Reads the state of the surrounding cells and generates
     *         a score based on the sum of the states of each cell. States
     *         have a value of either 0 (DEAD) or 1 (ALIVE), and so the
     *         maximum score is 8 (all surrounding cells are alive) or
     *         0 (all surrounding cells are dead). This sum is passed to
     *         updateState(), which decides whether or not the cell should
     *         die or stay alive.
     */
    public void interact(Cell[] surroundingCells) {

    }

    /* MODIFIES: this.state
     * EFFECT: Changes the state of the cell based on the
     *         score that was passed to it from interact(). The
     *         response changes based on the current state of the cell.
     *         ALIVE: die if count > 3 or count < 2
     *         DEAD: alive if count == 3;
     */
    public void updateState(int score) {

    }

}
