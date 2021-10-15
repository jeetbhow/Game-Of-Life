package ui;

import model.Board;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Executes the UI.
public class App {

    /*
     * EFFECTS: Creates an instance of the app.
     */
    public App() {
        run();
    }

    /*
     * MODIFIES: Board.height, Board.width.
     * EFFECTS: Starts the application.
     */
    public static void run() {
        introduction();
        Board temp = new Board(1, 1);
        System.out.println(temp);
        System.out.println("This is the game board. It contains a single cell.\nAdd cells to the "
                + "board by typing in \"1\" or \"2\".\nIf you're feeling lazy, type in \"3\" to "
                + "set the board\nto a particular size and then \"5\" to assign a random configuration.\n");
        Board main = setUp(temp);
        System.out.println();
        System.out.println(main);
        System.out.println("Starting simulation . . .");
        runSimulation(main);
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
    public static Board setUp(Board board) {
        boolean isRunning = true;
        while (isRunning) {
            Scanner scanner = new Scanner(System.in);
            printCommands();
            String input = scanner.nextLine();
            if (input.equals("1")) {
                board.addRow();
            } else if (input.equals("2")) {
                board.addColumn();
            } else if (input.equals("3")) {
                board = setSize(scanner, board);
            } else if (input.equals("4")) {
                board = flipState(scanner, board);
            } else if (input.equals("5")) {
                board.randomize();
            } else if (input.equals("6")) {
                isRunning = false;
            }
            System.out.println(board);
        }
        return board;
    }

    /*
     * MODIFIES: Board.cells
     * EFFECTS: Set the board to a square board of size n.
     */
    public static Board setSize(Scanner scanner, Board board) {
        System.out.println("What size do you want to set the board to?");
        String input = scanner.nextLine();
        int size = Integer.parseInt(input);
        return new Board(size, size);
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
        System.out.println("[5] Randomize: Assign the board to a random configuration.");
        System.out.println("[6] Run Simulation: start the simulation.");
    }

    /*
     * MODIFIES: Board.cells
     * EFFECTS: Flips the state of a cell on the board.
     */
    public static Board flipState(Scanner scanner, Board board) {
        System.out.println("Type in an index in the format [row, column] to flip it.");
        String input = scanner.nextLine();
        board.flipCell(Integer.parseInt(input.substring(1, 2)),
                Integer.parseInt(input.substring(3, 4)));
        return board;
    }

    /*
     * EFFECTS: Board is non-empty.
     * MODIFIES: Board.cells
     * EFFECTS: Runs the simulation.
     */
    public static void runSimulation(Board board) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n" + board.update());
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
