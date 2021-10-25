package persistence;

import model.Board;
import model.Cell;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/* Reads a JSON file and converts it into a board */
public class JsonReader {
    String source;


    /*
     * EFFECTS: Initializes a JsonReader.
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: Read data from a JSON file and convert it into Board data.
     */
    public Board read() throws IOException {
        JSONObject data = readFile();
        Board board = parse(data);
        return board;
    }

    /*
     * EFFECTS: Takes text data from a .json file and converts it into a JSONObject
     */
    private JSONObject readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s));
        }
        return new JSONObject(sb.toString());
    }

    /*
     * EFFECTS: Parses the data from the JSONObject and converts it into a Board.
     */
    private Board parse(JSONObject json) {
        int height = json.getInt("height");
        int width = json.getInt("width");
        JSONArray states = json.getJSONArray("states");
        ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell();
                int state = states.getJSONArray(i).getInt(j);
                if (state == 1) {
                    cell.flip();
                }
                cells.get(i).add(cell);
            }
        }
        return new Board(height, width, cells);
    }

}
