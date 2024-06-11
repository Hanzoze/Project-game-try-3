package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //for closing in right way
        window.setResizable(false);
        window.setTitle("Super-mega-nieciekawa-gra"); //name

        // Создайте GamePanel без ControlPanel
        GamePanel gamePanel = new GamePanel(new SimulationSettings(0, 0, 10, 10));

        // Затем создайте ControlPanel с ссылкой на GamePanel
        ControlPanel controlPanel = new ControlPanel(gamePanel);

        // Установите ControlPanel в GamePanel
        gamePanel.setControlPanel(controlPanel);

        window.add(gamePanel); //adding gamePanel
        window.pack();

        window.setLocationRelativeTo(null); //center of the screen
        window.setVisible(true); //for game window be visable

        gamePanel.startGameThread(); //to start game
    }
}
