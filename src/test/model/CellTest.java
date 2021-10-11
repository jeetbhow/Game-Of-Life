package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;

    @BeforeEach
    void initialize() {
        cell1 = new Cell();
        cell2 = new Cell(State.ALIVE);
        cell3 = new Cell(State.DEAD);
        cell4 = new Cell(State.DEAD);
    }

    @Test
    @DisplayName("Default constructor tests")
    void defaultConstructorTest() {
        assertSame(cell1.getState(), State.DEAD);
        assertNotSame(cell1.getState(), State.ALIVE);
    }

    @Test
    @DisplayName("Alternate constructor tests")
    void alternateConstructorTest() {
        assertSame(cell2.getState(), State.ALIVE);
        assertNotSame(cell2.getState(), State.DEAD);
        assertSame(cell3.getState(), State.DEAD);
        assertNotSame(cell3.getState(), State.ALIVE);
    }

    @Test
    @DisplayName("Equality Tests")
    void equalityTests() {
        String s = "";
        assertNotEquals(cell1, s);
        assertEquals(cell1, cell1);
        assertEquals(cell1, cell3);
        assertNotEquals(cell2, cell3);
    }

    @Test
    @DisplayName("toString tests")
    void toStringTests() {
        String dead = cell1.toString();
        String dead2 = cell4.toString();
        String alive = cell2.toString();
        String alive2 = cell2.toString();

        assertEquals("0", dead);
        assertEquals("1", alive);
        assertEquals("1", alive2);
        assertNotEquals(dead, alive);
        assertEquals(dead, dead2);
        assertEquals(alive, alive2);
    }
}
