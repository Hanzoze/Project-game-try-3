package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame {
    private JButton startStopButton;
    private JButton restartButton;
    private JLabel destroyedCounterLabel;
    private JLabel repairedCounterLabel;
    private JLabel countLabel;
    private GamePanel gamePanel;

    public ControlPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        setTitle("Control Panel");
        setSize(300, 200);
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startStopButton = new JButton("Start/Stop");
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gamePanel.isRunning()) {
                    gamePanel.stopGameThread();
                } else {
                    gamePanel.startGameThread();
                }
            }
        });

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.restartSimulation();
            }
        });

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
