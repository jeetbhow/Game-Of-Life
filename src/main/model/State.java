package model;

/* Class: State
 * A data container that stores the state of a cell. Cells can either be
 * dead (encoded as a 0), or alive (encoded as a 1).
 */

public enum State {
    DEAD(0),
    ALIVE(1);
    public final int value;

    State(int value) {
        this.value = value;
    }

    public String toString() {
        String s = "";
        return s += value;
    }
}
