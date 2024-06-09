package Main;

import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16x16
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int destroyedCounter = 0; // Counter for destroyed tiles
    int repairedCounter = 0;
    int count = 0;

    private final SimulationSettings settings;

    final int FPS = 30;
    Thread gameThread;
    Ground[][] groundArray;
    List<Creature> creatures; // List to store all creatures
    List<Structure> structures; // List to store all structures
    ControlPanel controlPanel;

    // Constructor for GamePanel
    public GamePanel(ControlPanel controlPanel, SimulationSettings settings) {
        this.controlPanel = controlPanel;
        this.settings = settings;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        groundArray = Ground.creatnewground(maxScreenCol, maxScreenRow, tileSize, Color.GRAY);
        creatures = new ArrayList<>();
        structures = new ArrayList<>();

        createWarriors(settings.getNumberOfWarriorsRed(), Color.RED);
        createWarriors(settings.getNumberOfWarriorsBlue(), Color.BLUE);
        createBuilders(settings.getNumberOfBuilders(), Color.YELLOW);
        createBombs(settings.getNumberOfBombs(), Color.ORANGE);
    }

    private void createWarriors(int numberOfWarriors, Color color) {
        for (int i = 0; i < numberOfWarriors; i++) {
            Warrior warrior = Warrior.createRandomWarrior(maxScreenCol, maxScreenRow, tileSize, color);
            creatures.add(warrior);
        }
    }

    private void createBuilders(int numberOfBuilders, Color color) {
        for (int i = 0; i < numberOfBuilders; i++) {
            Builder builder = Builder.createRandomBuilder(maxScreenCol, maxScreenRow, tileSize, color);
            creatures.add(builder);
        }
    }

    private void createBombs(int numberOfBombs, Color color) {
        for (int i = 0; i < numberOfBombs; i++) {
            Bomb bomb = Bomb.createRandomBomb(maxScreenCol, maxScreenRow, tileSize, color);
            structures.add(bomb);
        }
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void startGameThread() {
        if (gameThread == null || !gameThread.isAlive()) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stopGameThread() {
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
    }

    public boolean isRunning() {
        return gameThread != null && gameThread.isAlive();
    }

    public void restartSimulation() {
        // Reset counters
        destroyedCounter = 0;
        repairedCounter = 0;
        count = 0;

        // Clear lists of creatures and structures
        creatures.clear();
        structures.clear();

        // Recreate creatures
        for (int i = 0; i < settings.getNumberOfWarriorsRed(); i++) {
            creatures.add(Warrior.createRandomWarrior(maxScreenCol, maxScreenRow, tileSize, Color.RED));
        }
        for (int i = 0; i < settings.getNumberOfWarriorsBlue(); i++) {
            creatures.add(Warrior.createRandomWarrior(maxScreenCol, maxScreenRow, tileSize, Color.BLUE));
        }
        for (int i = 0; i < settings.getNumberOfBuilders(); i++) {
            creatures.add(Builder.createRandomBuilder(maxScreenCol, maxScreenRow, tileSize, Color.YELLOW));
        }

        // Recreate structures
        for (int i = 0; i < settings.getNumberOfBombs(); i++) {
            structures.add(Bomb.createRandomBomb(maxScreenCol, maxScreenRow, tileSize, Color.ORANGE));
        }

        // Restore all ground tiles
        for (int i = 0; i < maxScreenCol; i++) {
            for (int j = 0; j < maxScreenRow; j++) {
                groundArray[i][j].setAlive();
            }
        }

        updateControlPanel();
    }


    public void incrementRepairedCounter() {
        repairedCounter++;
        updateControlPanel();
    }
    public void incrementDestroyedCounter() {
        destroyedCounter++;
        updateControlPanel();
    }
    public void incrementCounter() {
        count++;
        updateControlPanel();
    }

    private void updateControlPanel() {
        controlPanel.updateCounters(destroyedCounter, repairedCounter, count);
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.033333 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        for (Creature creature : creatures) {
            if (creature.isAlive()) {
                Creature.move(creature, tileSize, screenWidth, screenHeight);
                if (creature instanceof Warrior) {
                    ((Warrior) creature).handleEncounter(creatures, groundArray, tileSize, this);
                } else if (creature instanceof Builder) {
                    ((Builder) creature).repairGroundIfBroken(groundArray, tileSize, this);
                }
            }
        }

        for (Structure structure : structures) {
            if (structure.isAlive()) {
                if (structure instanceof Bomb) {
                    ((Bomb) structure).handleCollision(creatures, structures, groundArray, tileSize, this);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Drawing the ground
        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
                groundArray[x][y].draw(g2d);
                groundArray[x][y].drawBorder(g2d);
                if (!groundArray[x][y].isAlive()) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }

        // Drawing creatures
        for (Creature creature : creatures) {
            creature.draw(g2d);
        }

        // Drawing structures
        for (Structure structure : structures) {
            structure.draw(g2d);
        }
    }
}
