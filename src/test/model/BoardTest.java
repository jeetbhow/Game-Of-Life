package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board testBoard;
    final String ALL_DEAD = "| . | . | . |\n" +
                            "| . | . | . |\n" +
                            "| . | . | . |";

    final String CENTER_ALIVE = "| . | . | . |\n" +
                                "| . | O | . |\n" +
                                "| . | . | . |";

    final String FOUR_SQUARE = "| . | . | . | . |\n" +
                               "| . | . | . | . |\n" +
                               "| . | . | . | . |\n" +
                               "| . | . | . | . |";

    final String FOUR_SQUARE_ALIVE = "| . | . | . | . |\n" +
                                     "| . | . | . | . |\n" +
                                     "| . | . | . | . |\n" +
                                     "| . | . | . | O |";

    final String BLINKER_1 = "| . | O | . |\n" +
                             "| . | O | . |\n" +
                             "| . | O | . |";

    final String BLINKER_2 = "| . | . | . |\n" +
                             "| O | O | O |\n" +
                             "| . | . | . |";

    final String BLOCK = "| . | O | O |\n" +
                         "| . | O | O |\n" +
                         "| . | . | . |";

    @BeforeEach
    void initialize() {
        testBoard = new Board(3, 3);
    }


    @Test
    void constructor() {
        assertEquals(3, testBoard.getWidth());
        assertEquals(3, testBoard.getHeight());
    }

    @Test
    void toStringTests() {
        assertEquals(ALL_DEAD, testBoard.toString());
    }

    @Test
    void addRowsAndAddColumns() {
        testBoard.addColumn();
        testBoard.addRow();
        assertEquals(FOUR_SQUARE, testBoard.toString());
    }

    @Test
    void flipCell() {
        testBoard.flipCell(1,1);
        assertEquals(CENTER_ALIVE, testBoard.toString());

        testBoard.flipCell(1,1);
        assertNotEquals(CENTER_ALIVE, testBoard.toString());
        assertEquals(ALL_DEAD, testBoard.toString());

        testBoard.addColumn();
        testBoard.addRow();
        testBoard.flipCell(3,3);
        assertEquals(FOUR_SQUARE_ALIVE, testBoard.toString());
    }

    @Test
    void scan() {
        String corner = "[., ., .]";
        String side = "[., ., ., ., .]";
        String center = "[., ., ., ., ., ., ., .]";

        assertEquals(corner, testBoard.scan(0,0).toString());
        assertEquals(corner, testBoard.scan(0,2).toString());
        assertEquals(corner, testBoard.scan(2,0).toString());
        assertEquals(corner, testBoard.scan(2,2).toString());

        assertEquals(side, testBoard.scan(0,1).toString());
        assertEquals(side, testBoard.scan(1,0).toString());
        assertEquals(side, testBoard.scan(2,1).toString());
        assertEquals(side, testBoard.scan(1, 2).toString());

        assertEquals(center, testBoard.scan(1,1).toString());
    }

    @Test
    void update() {
        testBoard.stringToBoard(BLINKER_1, 3, 3);
        System.out.println(testBoard);
        assertEquals(BLINKER_2, testBoard.update().toString());
        System.out.println(testBoard);

        testBoard.stringToBoard(BLOCK, 3, 3);
        assertEquals(BLOCK, testBoard.update().toString());
        System.out.println(testBoard);
    }

    @Test
    void randomize() {
        Board prev = new Board(testBoard);
        testBoard.randomize();
        assertNotEquals(prev, testBoard);
    }

    @Test
    void removeColumn() {
        testBoard.removeColumn();
        Board testBoardOneLessColumn = new Board(2,3);
        assertEquals(testBoard, testBoardOneLessColumn);
    }

    @Test
    void removeRow() {
        // Remove a row from the testBoard.
        testBoard.removeRow();
        // Make a new board of dead cells with one less row.
        Board testBoardOneLessColumn = new Board(3, 2);
        assertEquals(testBoardOneLessColumn, testBoard);
    }

}
