package ui;

import model.Board;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        introduction();
        Board temp = new Board(1, 1);
        System.out.println(temp);
        Board main = setUp(temp);
        System.out.println(main);
        System.out.println("Starting game . . .");
        runSimulation(main);
    }

    public static void introduction() {
        System.out.println("***************************");
        System.out.println("WELCOME TO THE GAME OF LIFE");
        System.out.println("***************************");
    }

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
                System.out.println("Type in an index in the format [row, column] to flip it.");
                input = scanner.nextLine();
                board.flipCell(Integer.parseInt(input.substring(1, 2)),
                        Integer.parseInt(input.substring(3, 4)));
            } else if (input.equals("4")) {
                board.randomize();
            } else if (input.equals("5")) {
                isRunning = false;
            }
            System.out.println(board);
        }
        return board;
    }

    public static void printCommands() {
        System.out.println("Commands: ");
        System.out.println("[1] Add row");
        System.out.println("[2] Add column");
        System.out.println("[3] Flip state");
        System.out.println("[4] Randomize");
        System.out.println("[5] Run Simulation");
    }

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


