package persistence;

import model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    private final String DESTINATION = "./data/board.json";
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
    void deadBoard() {
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
