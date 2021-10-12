package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.State.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board testBoard;
    final String ALL_DEAD = "| . | . | . |\n" +
                            "| . | . | . |\n" +
                            "| . | . | . |";

    final String CENTER_ALIVE = "| . | . | . |\n" +
                                "| . | * | . |\n" +
                                "| . | . | . |";

    final String FOUR_SQUARE = "| . | . | . | . |\n" +
                               "| . | . | . | . |\n" +
                               "| . | . | . | . |\n" +
                               "| . | . | . | . |";

    final String FOUR_SQUARE_ALIVE = "| . | . | . | . |\n" +
                                     "| . | . | . | . |\n" +
                                     "| . | . | . | . |\n" +
                                     "| . | . | . | * |";


    @BeforeEach
    void initialize() {
        testBoard = new Board(3, 3);
    }


    @Test
    @DisplayName("Constructor tests")
    void constructor() {
        assertEquals(3, testBoard.width);
        assertEquals(3, testBoard.height);
    }

    @Test
    @DisplayName("toString tests")
    void toStringTests() {
        assertEquals(ALL_DEAD, testBoard.toString());
    }

    @Test
    @DisplayName("addRows and addColumns")
    void addRowsAndAddColumns() {
        // Expecting a board with 4 rows and columns.
        testBoard.addColumn();
        testBoard.addRow();
        assertEquals(FOUR_SQUARE, testBoard.toString());
    }

    @Test
    @DisplayName("flipCell tests")
    void flipCellTests() {
        // Expecting the center cell to flip to ALIVE.
        testBoard.flipCell(1,1);
        assertEquals(CENTER_ALIVE, testBoard.toString());

        // Expecting the center cell to flip back to DEAD.
        testBoard.flipCell(1,1);
        assertNotEquals(CENTER_ALIVE, testBoard.toString());
        assertEquals(ALL_DEAD, testBoard.toString());

        // Expecting the new row and column to work with flipCell().
        // The cell on (3,3) should go from DEAD to ALIVE.
        testBoard.addColumn();
        testBoard.addRow();
        testBoard.flipCell(3,3);
        assertEquals(FOUR_SQUARE_ALIVE, testBoard.toString());
    }
}
