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

    private SimulationPanel simulationPanel;
    private UIPanel uiPanel;
    private Board board;
    private Timer timer;
    private JsonReader reader;
    private JsonWriter writer;

    // EFFECTS: Instantiates the Game Of Life.
    public GameOfLife() {
        board = new Board(10,10);
        simulationPanel = new SimulationPanel(board);
        uiPanel = new UIPanel(board, simulationPanel);
        timer = new Timer(75, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.update();
                simulationPanel.repaint();
            }
        });
        reader = new JsonReader(SOURCE);
        writer = new JsonWriter(SOURCE);
        initializeUIPanel();
        initializeFrame();
    }

    private void initializeUIPanel() {
        JButton randomize = new JButton("Randomize");
        randomize.setActionCommand("randomize");
        randomize.addActionListener(this);
        JButton run = new JButton("Run");
        run.setActionCommand("run");
        run.addActionListener(this);
        JButton pause = new JButton(("Pause"));
        pause.setActionCommand("pause");
        pause.addActionListener(this);
        JSlider slider = new JSlider(10,200,10);
        slider.setMajorTickSpacing(50);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(this);
        JButton save = new JButton("Save");
        save.setActionCommand("Save");
        save.addActionListener(this);
        JButton load = new JButton("Load");
        load.setActionCommand("Load");
        load.addActionListener(this);
        uiPanel.add(save);
        uiPanel.add(load);
        uiPanel.add(randomize);
        uiPanel.add(run);
        uiPanel.add(pause);
        uiPanel.add(slider);
    }

    // EFFECTS: Sets up JFrame parameters.
    private void initializeFrame() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        pack();
        setVisible(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponents();
    }

    private void addComponents() {
        add(simulationPanel, BorderLayout.PAGE_START);
        add(uiPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("run")) {
            timer.start();
        } else if (e.getActionCommand().equals("pause")) {
            timer.stop();
        } else if (e.getActionCommand().equals("randomize")) {
            board.randomize();
            simulationPanel.repaint();
        } else if (e.getActionCommand().equals("Save")) {
            try {
                writer.open();
                writer.write(board);
            } catch (IOException exception) {
                System.out.println("Could not save file.");
            }
            writer.close();
        } else if (e.getActionCommand().equals("Load")) {
            System.out.println("Here");
            try {
                board = reader.read();
                simulationPanel.setBoard(board);
                simulationPanel.repaint();
            } catch (IOException exception) {
                System.out.println("Could not load file");
            }
        }
        if (e.getSource() == timer) {
            timer.start();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider js = (JSlider) e.getSource();
        board.setSize(js.getValue());
        simulationPanel.calculateAndSetUnitSize();
        simulationPanel.repaint();
    }
}
