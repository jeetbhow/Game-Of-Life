package ui;

import model.Board;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
    Represents the window in which the simulation is displayed.
 */
public class GameOfLife extends JFrame implements ActionListener, ChangeListener {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final String SOURCE = "./data/board.json";
    public static final String RANDOMIZE = "Randomize";
    public static final String RUN = "Run";
    public static final String PAUSE = "Pause";
    public static final String SAVE = "Save";
    public static final String LOAD = "Load";

    private SimulationPanel simulationPanel;
    private UIPanel uiPanel;
    private Board board;
    private Timer timer;
    private JsonReader reader;
    private JsonWriter writer;

    /*
     * EFFECTS: Instantiates the Game Of Life.
     */
    public GameOfLife() {
        initializeSwingComponents();
        initializeTimer();
        initializePersistence();
    }

    /*
     * MODIFIES: this, JsonReader.source, JsonWriter.destination, JsonWriter.writer
     * EFFECTS: Initializes the persistence components.
     */
    private void initializePersistence() {
        reader = new JsonReader(SOURCE);
        writer = new JsonWriter(SOURCE);
    }

    /*
     * MODIFIES: this, Timer.delay.
     * EFFECTS: Initializes the simulation timer.
     */
    private void initializeTimer() {
        timer = new Timer(75, e -> {
            board.update();
            simulationPanel.repaint();
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: Initializes the Swing components.
     */
    private void initializeSwingComponents() {
        board = new Board(10,10);
        simulationPanel = new SimulationPanel(board);
        uiPanel = new UIPanel(board, simulationPanel);
        initializeUIPanel();
        initializeFrame();
    }


    private void initializeUIPanel() {
        addButtons();
        setUpSizeSlider();
    }

    /*
     * MODIFIES this, uiPanel, JSlider.paintTicks, JSlider.paintLabels, JSlider.snapToTicks
     * EFFECTS: Sets up the size slider.
     */
    private void setUpSizeSlider() {
        JSlider slider = new JSlider(10,200,10);
        slider.setMajorTickSpacing(50);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(this);
        uiPanel.add(slider);
    }

    /*
     * MODIFIES: this, UIPanel
     * EFFECTS: Adds buttons to the UIPanel.
     */
    private void addButtons() {
        JButton randomize = new JButton(RANDOMIZE);
        randomize.setActionCommand(RANDOMIZE);
        randomize.addActionListener(this);
        JButton run = new JButton(RUN);
        run.setActionCommand(RUN);
        run.addActionListener(this);
        JButton pause = new JButton(PAUSE);
        pause.setActionCommand(PAUSE);
        pause.addActionListener(this);
        JButton save = new JButton(SAVE);
        save.setActionCommand(SAVE);
        save.addActionListener(this);
        JButton load = new JButton(LOAD);
        load.setActionCommand(LOAD);
        load.addActionListener(this);
        uiPanel.add(save);
        uiPanel.add(load);
        uiPanel.add(randomize);
        uiPanel.add(run);
        uiPanel.add(pause);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Initializes the JFrame.
     */
    private void initializeFrame() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setVisible(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponents();
        pack();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds the SimulationPanel and UIPanel to the JFrame.
     */
    private void addComponents() {
        add(simulationPanel, BorderLayout.PAGE_START);
        add(uiPanel, BorderLayout.PAGE_END);
    }

    /*
     * MODIFIES: this, board.cells, board.width, board.height, simulationPanel.board,
     * JsonWriter.printer.
     * EFFECTS: Responds to button presses on the UI.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        pauseOrRun(e);
        randomize(e);
        checkPersistence(e);
        activateTimer(e);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Starts the timer.
     */
    private void activateTimer(ActionEvent e) {
        if (e.getSource() == timer) {
            timer.start();
        }
    }

    /*
     * MODIFIES: this. board.cells
     * EFFECTS: Randomizes the board.
     */
    private void randomize(ActionEvent e) {
        if (e.getActionCommand().equals(RANDOMIZE)) {
            board.randomize();
            simulationPanel.repaint();
        }
    }

    /*
     * MODIFIES: this, writer.printer, board.height, board.width
     * EFFECTS: Saves or Loads board data.
     */
    private void checkPersistence(ActionEvent e) {
        if (e.getActionCommand().equals(SAVE)) {
            try {
                writer.open();
                writer.write(board);
            } catch (IOException exception) {
                //
            }
            writer.close();
        } else if (e.getActionCommand().equals(LOAD)) {
            try {
                board = reader.read();
                simulationPanel.setBoard(board);
                simulationPanel.repaint();
            } catch (IOException exception) {
                //
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Starts or pauses the simulation
     */
    private void pauseOrRun(ActionEvent e) {
        if (e.getActionCommand().equals(RUN)) {
            timer.start();
        } else if (e.getActionCommand().equals(PAUSE)) {
            timer.stop();
        }
    }

    /*
     * MODIFIES: this, board.size, simulationPanel.pixelWidth, simulationPanel.pixelHeight.
     * EFFECTS: Changes the size of the board when the size slider is moved.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider js = (JSlider) e.getSource();
        board.setSize(js.getValue());
        simulationPanel.calculateAndSetUnitSize();
        simulationPanel.repaint();
    }
}
