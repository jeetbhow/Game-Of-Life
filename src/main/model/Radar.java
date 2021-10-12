package model;

/* A Radar is capable of detecting whether or not there are cells to the
   left, right, top, or bottom, or corners of a particular location. It
   uses this information to send instructions back to the Board which
    helps it update it's own state. */

public class Radar {
    int row;
    int column;
    boolean[] message;

    /* EFFECTS: Initializes the radar based on the given row and column */
    public Radar(int row, int column) {

    }

    /* MODIFIES: this.
     * EFFECTS: Checks the surrounding area to see if there are any cells nearby.
     * Returns a message back to the board based on what it found.  */
    public boolean[] scan() {

        return null;
    }
}
