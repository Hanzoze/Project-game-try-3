package Entity;

import Main.GamePanel;
import java.awt.*;
import java.util.Random;

/**
 * Represents a builder in the game.
 */
public class Builder extends Creature {
    /**
     * Creates a new Builder instance with the specified parameters.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size  the size of the builder
     * @param color  the color of the builder
     */
    public Builder(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    /**
     * Creates a random builder within the specified constraints.
     *
     * @param maxScreenCol the maximum screen columns
     * @param maxScreenRow the maximum screen rows
     * @param tileSize     the size of the tiles
     * @param color        the color of the builder
     * @return a new Builder instance
     */
    public static Builder createRandomBuilder(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Builder(randomX, randomY, tileSize, color);
    }

    /**
     * Repairs ground if it is broken.
     *
     * @param groundArray the array of ground tiles
     * @param tileSize    the size of the tiles
     * @param gamePanel   the game panel
     */
    public void repairGroundIfBroken(Ground[][] groundArray, int tileSize, GamePanel gamePanel) {
        int col = getX() / tileSize; //searching for coordinates within 2D array ground
        int row = getY() / tileSize; //searching for coordinates within 2D array ground
        Ground ground = groundArray[col][row];
        if (!ground.isAlive()) { //repairing the broken ground on the same tile where Builder is set
            ground.setAlive();
            gamePanel.incrementRepairedCounter();
        }
    }
}
