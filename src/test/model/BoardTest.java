package model;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    static final String DEAD =  "| . | . | . |\n" +
                                "| . | . | . |\n" +
                                "| . | . | . |";


    @Test
    @DisplayName("Constructor Tests")
    void constructor() {
        Board board1 = new Board(3);
        assertEquals(DEAD, board1.toString());
    }

}
