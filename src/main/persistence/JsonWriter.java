package persistence;

import model.Board;
import org.json.JSONObject;
import java.io.*;

// An object that can save a JSON file containing the configuration of a Board.
// Based off of the JSON writer class in JsonSerializationDemo from CPSC 210.

public class JsonWriter {
    private static final int TAB = 2;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: If the file exists, then loads it onto writer. If not, then
    // throws a FileNotFoundException.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: Writes a JSON representation of board to file.
    public void write(Board board) {
        JSONObject json = board.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: Closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: Writes string to file.
    private void saveToFile(String json) {
        writer.print(json);
    }
}
