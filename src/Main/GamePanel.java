package Main;

import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3; //for proper scaling GamePanel on the screen

    final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int destroyedCounter = 0; // Counter for destroyed ground
    int repairedCounter = 0; //Counter for repaired ground
    int count = 0; //Counter for Builders killed by Warriors encounter

    private final SimulationSettings settings;

    final int FPS = 30; //frames per second and also speed for simulation
    Thread gameThread; //gameThread for keeping the program runnable before user want to stop it
    Ground[][] groundArray; //2D array to store all ground
    List<Creature> creatures; //List to store all creatures
    List<Structure> structures; //List to store all structures
    ControlPanel controlPanel;

    // Constructor for GamePanel
    public GamePanel(SimulationSettings settings) {
        this.settings = settings;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        groundArray = Ground.creatnewground(maxScreenCol, maxScreenRow, tileSize, Color.GRAY);
        creatures = new ArrayList<>();
        structures = new ArrayList<>();

        //creating all Entity with settings provided from SimulationSettings
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

    //to set ControlPanel inside GamePanel
    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    //Starting game
    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    //Stopping game
    public void stopGameThread() {
        if (gameThread != null) {
            gameThread.interrupt();
            gameThread = null;
        }
    }

    public boolean isRunning() {
        return gameThread != null && gameThread.isAlive();
    }

    //restarting simulation with the same settings
    public void restartSimulation() {
        // Reset counters
        destroyedCounter = 0;
        repairedCounter = 0;
        count = 0;

        // Clear lists of creatures and structures
        creatures.clear();
        structures.clear();

        // Recreate creatures
        createWarriors(settings.getNumberOfWarriorsRed(), Color.RED);
        createWarriors(settings.getNumberOfWarriorsBlue(), Color.BLUE);
        createBuilders(settings.getNumberOfBuilders(), Color.YELLOW);
        createBombs(settings.getNumberOfBombs(), Color.ORANGE);

        // Restore all ground tiles
        groundArray = Ground.creatnewground(maxScreenCol, maxScreenRow, tileSize, Color.GRAY);

        //Update ControlPanel
        updateControlPanel();
    }

    //Increment for all counters
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

    //updating ControlPanel with new values for counters
    private void updateControlPanel() {
        controlPanel.updateCounters(destroyedCounter, repairedCounter, count);
    }

    //method keep program running with correct numbers of frames per second on screen
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; //We look for how much time has passed from current time by subtracting from it last time that we know. Then by dividing it on drawInterval we check if it has achived time needed for update
            lastTime = currentTime; //Then we change our last time for current time so the loop will work properly
            if (delta >= 1) {
                update();
                repaint();
                delta--; //and in the end we are subtracting 1 from delta, so it will be the same from the start
            }
        }
    }

    //method with all encounters
    public void update() {
        //encounters for creature
        for (Creature creature : creatures) {
            if (creature.isAlive()) { //only for alive creature
                Creature.move(creature, tileSize, screenWidth, screenHeight);
                if (creature instanceof Warrior) {
                    ((Warrior) creature).handleEncounter(creatures, groundArray, tileSize, this);
                } else if (creature instanceof Builder) {
                    ((Builder) creature).repairGroundIfBroken(groundArray, tileSize, this);
                }
            }
        }

        //encounters for structure
        for (Structure structure : structures) {
            if (structure.isAlive()) { //only for alive structure
                if (structure instanceof Bomb) {
                    ((Bomb) structure).handleEncounter(creatures, structures, groundArray, tileSize, this);
                }
            }
        }
    }

    //method for correct painting
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Drawing the ground
        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
                if(groundArray[x][y].isAlive){
                    groundArray[x][y].draw(g2d);
                    groundArray[x][y].drawBorder(g2d);
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
