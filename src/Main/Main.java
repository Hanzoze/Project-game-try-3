package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //for closing in right way
        window.setResizable(false);
        window.setTitle("Kolorowe kwadraciki"); //name
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null); //starting location on screen
        window.setVisible(true); //visibility
        gamePanel.startGameThread();
    }
}