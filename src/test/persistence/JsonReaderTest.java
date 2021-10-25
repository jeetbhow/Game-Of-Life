package persistence;

import model.Board;
import org.json.JSONObject;
import persistence.JsonReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest {
    private JsonReader jsonReader;
    private final String SOURCE = "./data/test.json";
    private final String ALL_DEAD = "| . | . | . |\n" +
                                    "| . | . | . |\n" +
                                    "| . | . | . |";

    @BeforeEach
    void init() {
        jsonReader = new JsonReader(SOURCE);
    }

    @Test
    void read() {
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
}
