package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The ControlPanel class represents the control interface for the simulation,
 * providing buttons to start/stop and restart the simulation, as well as labels
 * to display counters.
 */
public class ControlPanel extends JFrame {
    //Labels for all counters
    private final JLabel destroyedCounterLabel;
    private final JLabel repairedCounterLabel;
    private final JLabel countLabel;

    /**
     * Constructor for the ControlPanel.
     *
     * @param gamePanel the GamePanel instance to control the simulation
     */
    public ControlPanel(GamePanel gamePanel) {

        setTitle("Control Panel"); //setting name
        setSize(300, 200); //setting size
        setLayout(new GridLayout(5, 1)); //for 5 different elements in one column
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //for closing in right way

        //creating button to stop and start simulation
        JButton startStopButton = new JButton("Start/Stop");
        startStopButton.addActionListener(e -> {
            if (gamePanel.isRunning()) {
                gamePanel.stopGameThread();
            } else {
                gamePanel.startGameThread();
            }
        });

        //creating button to restart simulation
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> gamePanel.restartSimulation());

        //creating labels for counters on the screen
        destroyedCounterLabel = new JLabel("Destroyed: 0");
        repairedCounterLabel = new JLabel("Repaired: 0");
        countLabel = new JLabel("Count: 0");

        //adding all elements
        add(startStopButton);
        add(restartButton);
        add(destroyedCounterLabel);
        add(repairedCounterLabel);
        add(countLabel);

        setLocationRelativeTo(null); // Center the control panel
        setVisible(true);
    }

    /**
     * Updates the counter labels with new information.
     *
     * @param destroyed the number of destroyed entities
     * @param repaired the number of repaired entities
     * @param count the current count
     */
    public void updateCounters(int destroyed, int repaired, int count) {
        destroyedCounterLabel.setText("Destroyed: " + destroyed);
        repairedCounterLabel.setText("Repaired: " + repaired);
        countLabel.setText("Count: " + count);
    }

}
