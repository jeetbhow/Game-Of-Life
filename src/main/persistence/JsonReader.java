package persistence;

import model.Board;
import model.Cell;
import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
 * An object that reads a JSON file and converts it into a Board.
 * Based off of JsonReader.java from JsonSerializationDemo in CPSC 210.
 */

public class JsonReader {
    private final String source;

    /* EFFECTS: Initializes a JsonReader. */
    public JsonReader(String source) {
        this.source = source;
    }

    /* EFFECTS: Reads data from a JSON file and converts it into Board data.
    * If the file doesn't exist, then through an IOException. */
    public Board read() throws IOException {
        JSONObject data = readFile();
        return parse(data);
    }

    /* EFFECTS: Takes text data from a .json file and converts it into a JSONObject.
    *  Throws an IOException if the file doesn't exist. */
    private JSONObject readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8);
        stream.forEach(sb::append);
        return new JSONObject(sb.toString());
    }

    /* EFFECTS: Parses the data from the JSONObject and converts it into a Board. */
    private Board parse(JSONObject json) {
        int height = json.getInt("height");
        int width = json.getInt("width");
        JSONArray states = json.getJSONArray("states");
        ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell();
                JSONObject state = states.getJSONArray(i).getJSONObject(j);
                if (state.get("state").equals("ALIVE")) {
                    cell.flip();
                }
                cells.get(i).add(cell);
                EventLog.getInstance().logEvent(new Event("Cells added to Board."));
            }
        }
        return new Board(width, height, cells);
    }
}
