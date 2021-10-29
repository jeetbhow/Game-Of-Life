package persistence;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/* Based off of JsonWriterTest in JsonSerializationDemo from CPSC 210. */

public class JsonWriterTest {
    private final String DESTINATION = "./data/testWrite.json";
    private final String ALL_DEAD = "| . | . | . |\n" +
                                    "| . | . | . |\n" +
                                    "| . | . | . |";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Board testBoard;


    @BeforeEach
    void init() {
        jsonWriter = new JsonWriter(DESTINATION);
        jsonReader = new JsonReader(DESTINATION);
        testBoard = new Board(3, 3);
    }


    @Test
    void testWriterNoCells() {
        try {
            Board board = new Board(0,0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBoard.json");
            writer.open();
            writer.write(board);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBoard.json");
            board = reader.read();
            assertEquals(0, board.getWidth());
            assertEquals(0, board.getHeight());
            assertTrue(board.getCells().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("../d\0wrongformat.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGenericBoard() {
        testBoard.stringToBoard(ALL_DEAD,3,3);
        try {
            jsonWriter.open();
            jsonWriter.write(testBoard);
            jsonWriter.close();
            Board b = jsonReader.read();
            assertEquals(testBoard.toString(), b.toString());
        } catch (IOException e) {
            fail("file not found");
        }
    }

}
