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

    private SimulationPanel sp;
    private UIPanel uip;
    private Board board;
    private Timer t;
    private JsonReader reader;
    private JsonWriter writer;

    // EFFECTS: Instantiates the Game Of Life.
    public GameOfLife() {
        board = new Board(10,10);
        sp = new SimulationPanel(board);
        uip = new UIPanel(board, sp);
        t = new Timer(75, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.update();
                sp.repaint();
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
        uip.add(save);
        uip.add(load);
        uip.add(randomize);
        uip.add(run);
        uip.add(pause);
        uip.add(slider);
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
        add(sp, BorderLayout.PAGE_START);
        add(uip, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("run")) {
            t.start();
        } else if (e.getActionCommand().equals("pause")) {
            t.stop();
        } else if (e.getActionCommand().equals("randomize")) {
            board.randomize();
            sp.repaint();
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
                sp.setBoard(board);
                sp.repaint();
            } catch (IOException exception) {
                System.out.println("Could not load file");
            }
        }
        if (e.getSource() == t) {
            t.start();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider js = (JSlider) e.getSource();
        board.setSize(js.getValue());
        sp.calculateAndSetUnitSize();
        sp.repaint();
    }
}
