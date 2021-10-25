package ui;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.scene.input.KeyCode;
import model.Board;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Executes the UI.
public class App {
    private static String DESTINATION = "./data/board.json";
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private static Board board;
    private static Scanner scanner;

    /*
     * EFFECTS: Creates an instance of the app.
     */
    public App() {
        board = new Board(1,1);
        jsonWriter = new JsonWriter(DESTINATION);
        jsonReader = new JsonReader(DESTINATION);
        scanner = new Scanner(System.in);
        run();
    }

    /*
     * MODIFIES: Board.height, Board.width.
     * EFFECTS: Starts the application.
     */
    public void run() {
        introduction();
        System.out.println(board);
        System.out.println("This is the game board. It contains a single cell.\nAdd cells to the "
                + "board by typing in \"1\" or \"2\".\nIf you're feeling lazy, type in \"3\" to "
                + "set the board\nto a particular size and then \"5\" to assign a random configuration.\n");
        setUp();
        System.out.println();
        System.out.println(board);
        System.out.println("Starting simulation . . .");
        runSimulation();
    }

    /*
     * EFFECTS: Prints the introduction to the console.
     */
    public static void introduction() {
        System.out.println("***************************");
        System.out.println("WELCOME TO THE GAME OF LIFE");
        System.out.println("***************************");
        System.out.println("THE RULES: ");
        System.out.println(" - Cells that are surrounded by 2 or 3 other cells live on to the next generation.");
        System.out.println(" - Dead cells that are surrounded by exactly 3 cells revive in the next generation.\n");
    }

    /*
     * MODIFIES: Board.height, Board.width, Board.cells
     * EFFECTS: Configures the board based on user input.
     */
    public void setUp() {
        boolean isRunning = true;
        while (isRunning) {
            printCommands();
            String input = scanner.nextLine();
            if (input.equals("1")) {
                board.addRow();
            } else if (input.equals("2")) {
                board.addColumn();
            } else if (input.equals("3")) {
                setSize();
            } else if (input.equals("4")) {
                flipState();
            } else if (input.equals("5")) {
                board.randomize();
            } else if (input.equals("6")) {
                load();
            } else if (input.equals("7")) {
                isRunning = false;
            }
            System.out.println(board);
        }
    }

    /*
     * MODIFIES: Board.cells
     * EFFECTS: Set the board to a square board of size n.
     */
    public void setSize() {
        System.out.println("What size do you want to set the board to?");
        String input = scanner.nextLine();
        int size = Integer.parseInt(input);
        this.board = new Board(size, size);
    }

    public void load() {
        try {
            board = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Could not load file.");
        }
    }

    /*
     * EFFECTS: Prints the commands to the console.
     */
    public static void printCommands() {
        System.out.println("Commands: ");
        System.out.println("[1] Add row: add a row of dead cells.");
        System.out.println("[2] Add column: add a column of dead cells.");
        System.out.println("[3] Set size: set the board to a square board of a size n.");
        System.out.println("[4] Flip state: flip the state of a cell on the board.");
        System.out.println("[5] Randomize: assign the board to a random configuration.");
        System.out.println("[6] Load: load the board from a previous simulation.");
        System.out.println("[7] Run Simulation: start the simulation.");
    }

    /*
     * MODIFIES: Board.cells
     * EFFECTS: Flips the state of a cell on the board.
     */
    public void flipState() {
        System.out.println("Type in an index in the format [row, column] to flip it.");
        String input = scanner.nextLine();
        board.flipCell(Integer.parseInt(input.substring(1, 2)),
                Integer.parseInt(input.substring(3, 4)));
    }

    /*
     * EFFECTS: Board is non-empty.
     * MODIFIES: Board.cells
     * EFFECTS: Runs the simulation.
     */
    public void runSimulation() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                board = board.update();
                System.out.println("\n" + board.update());
                System.out.println("Type s to save the board and quit. You have to do it quickly.");
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
        scanForExit();
    }

    public void scanForExit() {
        while (scanner.hasNext()) {
            if (scanner.next().equals("s")) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(board);
                    System.out.println("Board saved in " + DESTINATION);
                    jsonWriter.close();
                } catch (IOException e) {
                    System.out.println("Could not save file.");
                } finally {
                    System.exit(0);
                }
            }
        }
    }
}
