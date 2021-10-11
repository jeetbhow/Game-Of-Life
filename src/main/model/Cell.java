package model;

import java.util.ArrayList;

import static model.State.*;


/* Class: Cell
 * The basic unit of the Game of Life. Cells contain a state
 * of either DEAD or ALIVE. They also store their own position
 * on the board. They can read the state of the surrounding cells
 * on the board and use that information to update their state.
 */
public class Cell {
    private State state;

    /*
     * EFFECT: Default constructor. Instantiates a dead cell.
     */
    public Cell() {
        this.state = DEAD;
    }

    /*
     * EFFECT: Alternate constructor. Instantiates a cell with the given state.
     */
    public Cell(State state) {
        this.state = state;
    }

    /* REQUIRES: surroundingCells.length > 0.
     * EFFECT: Reads the state of the surrounding cells and generates
     *         a score based on the sum of the states of each cell. States
     *         have a value of either 0 (DEAD) or 1 (ALIVE), and so the
     *         maximum score is 8 (all surrounding cells are alive) or
     *         0 (all surrounding cells are dead). This sum is passed to
     *         updateState(), which decides whether or not the cell should
     *         die or stay alive. Returns the score.
     */
    public int interact(ArrayList<Cell> surroundingCells) {
        int score = 0;
        for (Cell cell : surroundingCells) {
            score += cell.state.value;
        }
        updateState(score);
        return score;
    }

    /* MODIFIES: this.state
     * EFFECT: Changes the state of the cell based on the
     *         score that was passed to it from interact(). The
     *         response changes based on the current state of the cell.
     *         ALIVE: die if count > 3 or count < 2
     *         DEAD: alive if count == 3;
     */
    private void updateState(int score) {
        switch (this.state) {
            case DEAD:
                this.state = (score == 3) ? ALIVE : DEAD;
                break;
            case ALIVE:
                this.state = (score < 2 || score > 3) ? DEAD : ALIVE;
                break;
        }
    }

    /* EFFECT: Returns a string representation of the current state. */
    public String toString() {
        return (this.state == ALIVE) ? "*" : ".";
    }

    /* EFFECTS: Equality check for cells. Returns true if o has the same state or if it's
     *          the same object. Returns false if o isn't a cell, or if it doesn't have
     *          the same state.
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Cell)) {
            return false;
        } else {
            Cell cell = (Cell) o;
            return this.state == cell.state;
        }
    }

    public State getState() {
        return state;
    }
}
