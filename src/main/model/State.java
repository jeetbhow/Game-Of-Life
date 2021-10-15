package model;

/*
 * Represents the state of a cell.
 */

public enum State {
    DEAD(0),
    ALIVE(1);
    public final int value;

    State(int value) {
        this.value = value;
    }
}
