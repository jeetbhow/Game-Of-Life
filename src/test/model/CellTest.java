package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static model.State.*;

public class CellTest {
    Cell defaultCell;
    Cell aliveCell;
    Cell deadCell;
    Cell cell4;

    @BeforeEach
    void initialize() {
        defaultCell = new Cell();
        aliveCell = new Cell(ALIVE);
        deadCell = new Cell(DEAD);
        cell4 = new Cell(DEAD);
    }

    @Test
    @DisplayName("Default constructor tests")
    void defaultConstructorTest() {
        assertSame(defaultCell.getState(), DEAD);
        assertNotSame(defaultCell.getState(), ALIVE);
    }

    @Test
    @DisplayName("Alternate constructor tests")
    void alternateConstructorTest() {
        assertSame(aliveCell.getState(), ALIVE);
        assertNotSame(aliveCell.getState(), DEAD);
        assertSame(deadCell.getState(), DEAD);
        assertNotSame(deadCell.getState(), ALIVE);
    }

    @Test
    @DisplayName("equals() tests")
    void equalityTests() {
        String s = "";
        assertNotEquals(defaultCell, s);
        assertEquals(defaultCell, defaultCell);
        assertEquals(defaultCell, deadCell);
        assertNotEquals(aliveCell, deadCell);
    }

    @Test
    @DisplayName("toString() tests")
    void toStringTests() {
        String dead = defaultCell.toString();
        String dead2 = cell4.toString();
        String alive = aliveCell.toString();
        String alive2 = aliveCell.toString();

        assertEquals("0", dead);
        assertEquals("1", alive);
        assertEquals("1", alive2);
        assertNotEquals(dead, alive);
        assertEquals(dead, dead2);
        assertEquals(alive, alive2);
    }

    @Test
    @DisplayName("interact() tests")
    void interactTests() {
        // Contains an array of dead cells.
        ArrayList<Cell> deadCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            deadCells.add(deadCell);
        }

        // Contains an array of alive cells.
        ArrayList<Cell> aliveCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            aliveCells.add(aliveCell);
        }

        // Contains an array with 3 live cells.
        ArrayList<Cell> threeLiveCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < 3) {
                threeLiveCells.add(aliveCell);
            } else {
                threeLiveCells.add(deadCell);
            }
        }

        // Contains an array of 4 live cells.
        ArrayList<Cell> fourLiveCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                fourLiveCells.add(aliveCell);
            } else {
                fourLiveCells.add(deadCell);
            }
        }

        // Our test cell.
        Cell testCell = new Cell(ALIVE);
        assertEquals(testCell, aliveCell);
        System.out.println(testCell);

        // Expecting a score of 0 and for it to die.
        int score = testCell.interact(deadCells);
        assertEquals(0, score);
        assertEquals(testCell, deadCell);
        System.out.println(testCell);

        // Expecting a score of 8 and for it to remain dead.
        score = testCell.interact(aliveCells);
        assertEquals(8, score);
        assertEquals(testCell, deadCell);
        System.out.println(testCell);

        // Expecting a score of 3 and for it to revive.
        score = testCell.interact(threeLiveCells);
        assertEquals(3, score);
        assertEquals(testCell, aliveCell);
        System.out.println(testCell);

        // Expecting a score of 4 and for it to die.
        score = testCell.interact(fourLiveCells);
        assertEquals(4, score);
        assertEquals(testCell, deadCell);
        System.out.println(testCell);
    }
}
