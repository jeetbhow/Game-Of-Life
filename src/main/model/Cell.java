package model;

import java.util.ArrayList;
import java.util.Random;

import static model.State.*;

/*
 * The basic unit of the game. Each cell contains a state of either DEAD or ALIVE.
 * Cells interact with their neighbors and update their own state based on the amount
 * of live and dead cells in their vicinity.
 */
public class Cell {
    private State state;

    /* EFFECTS: Instantiates a dead cell. */
    public Cell() {
        this.state = DEAD;
    }

    /* EFFECTS: Instantiates a cell with the given state. */
    public Cell(State state) {
        this.state = state;
    }

    /* EFFECTS: Instantiates a copy of another Cell. */
    public Cell(Cell cell) {
        this.state = cell.getState();
    }

    /* REQUIRES: surroundingCells.length > 0.
     * MODIFIES: this
     * EFFECTS: Interact with neighbor cells to update state. */
    public int interact(ArrayList<Cell> surroundingCells) {
        int score = 0;
        for (Cell cell : surroundingCells) {
            score += cell.state.value;
        }
        updateState(score);
        return score;
    }

    /* MODIFIES: this
     * EFFECTS: Changes the state of the cell based on the
     * score that was passed to it from interact(). The
     * response changes based on the current state of the cell.
     * ALIVE: die if count > 3 or count < 2
     * DEAD: alive if count == 3;
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

    /*
     * MODIFIES: this
     * EFFECTS: Flips the current state to the opposite one.
     */
    public void flip() {
        switch (this.state) {
            case DEAD:
                this.state = ALIVE;
                break;
            case ALIVE:
                this.state = DEAD;
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Set cell to a random state.
     */
    public void randomize() {
        Random random = new Random();
        int state = random.nextInt(2);
        if (state == 0) {
            this.state = DEAD;
        } else {
            this.state = ALIVE;
        }
    }

    /* EFFECTS: Returns a string representation of the current state. */
    public String toString() {
        return (this.state == ALIVE) ? "O" : ".";
    }

    /*
     * EFFECTS: Returns true if o has the same state or if it's
     * the same object.
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

    /*
     * EFFECT: Returns the current state.
     */
    public State getState() {
        return state;
    }
}
