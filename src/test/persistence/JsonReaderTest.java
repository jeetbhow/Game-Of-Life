package persistence;

import model.Board;
import org.json.JSONObject;
import persistence.JsonReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/* Based off of JsonReaderTest in JsonSerializationDemo from CPSC 210. */

public class JsonReaderTest {
    private JsonReader jsonReader;
    private final String SOURCE = "./data/test.json";
    private final String SOURCE2 = "./data/test2.json";
    private final String ALL_DEAD = "| . | . | . |\n" +
                                    "| . | . | . |\n" +
                                    "| . | . | . |";
    private final String ALL_ALIVE = "| O | O | O |\n" +
                                     "| O | O | O |\n" +
                                     "| O | O | O |";

    @BeforeEach
    void init() {
        jsonReader = new JsonReader(SOURCE);
    }

    @Test
    void testReadNoSuchFile() {
        JsonReader reader = new JsonReader("./data/null.json");
        try {
            Board board = reader.read();
            fail("Exception wasn't caught");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyBoard() {
        JsonReader reader = new JsonReader("./data/emptyBoard.json");
        try {
            Board board = reader.read();
            assertEquals(0, board.getHeight());
            assertEquals(0,board.getWidth());
            assertTrue(board.getCells().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadWithGenericFile() {
        Board testBoard = new Board(3,3);
        testBoard.stringToBoard(ALL_DEAD, 3,3);
        Board jsonBoard = null;
        try {
            jsonBoard = jsonReader.read();
        } catch (IOException e) {
            fail("couldn't read file");
        }
        assertEquals(testBoard.toString(), jsonBoard.toString());
    }

    @Test
    void testReadWithAllAlive() {
        JsonReader reader2 = new JsonReader(SOURCE2);
        Board testBoard = new Board(3,3);
        testBoard.stringToBoard(ALL_ALIVE, 3,3);
        Board jsonBoard = null;
        try {
            jsonBoard = reader2.read();
        } catch (IOException e) {
            fail("couldn't read file");
        }
        assertEquals(testBoard.toString(), jsonBoard.toString());
    }
}
