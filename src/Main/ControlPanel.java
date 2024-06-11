package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame {
    private final JLabel destroyedCounterLabel;
    private final JLabel repairedCounterLabel;
    private final JLabel countLabel;

    public ControlPanel(GamePanel gamePanel) {

        setTitle("Control Panel");
        setSize(300, 200);
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton startStopButton = new JButton("Start/Stop");
        startStopButton.addActionListener(e -> {
            if (gamePanel.isRunning()) {
                gamePanel.stopGameThread();
            } else {
                gamePanel.startGameThread();
            }
        });

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> gamePanel.restartSimulation());

        destroyedCounterLabel = new JLabel("Destroyed: 0");
        repairedCounterLabel = new JLabel("Repaired: 0");
        countLabel = new JLabel("Count: 0");

        add(startStopButton);
        add(restartButton);
        add(destroyedCounterLabel);
        add(repairedCounterLabel);
        add(countLabel);

        setLocationRelativeTo(null); // Center the control panel
        setVisible(true);
    }

    public void updateCounters(int destroyed, int repaired, int count) {
        destroyedCounterLabel.setText("Destroyed: " + destroyed);
        repairedCounterLabel.setText("Repaired: " + repaired);
        countLabel.setText("Count: " + count);
    }

}
