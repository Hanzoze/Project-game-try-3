package Entity;

import Main.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Represents a bomb in the game.
 */
public class Bomb extends Structure {

    /**
     * Creates a new Bomb instance with the specified parameters.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param color  the color of the bomb
     */
    public Bomb(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    /**
     * Handles encounters between the bomb and other entities.
     *
     * @param creatures   the list of creatures
     * @param structures  the list of structures
     * @param groundArray the array of ground tiles
     * @param tileSize    the size of the tiles
     * @param gamePanel   the game panel
     */
    public void handleEncounter(List<Creature> creatures, List<Structure> structures, Ground[][] groundArray, int tileSize, GamePanel gamePanel) {
        for (Creature creature : creatures) { //collision check for creature
            if (this.isCollidingWith(creature)) {
                explode(groundArray, tileSize, creatures, structures, gamePanel);
                break;
            }
        }
        for (Structure structure : structures) { //collision check for structure
            if (this != structure && this.isCollidingWith(structure)) {
                explode(groundArray, tileSize, creatures, structures, gamePanel);
                break;
            }
        }
    }

    /**
     * Causes the bomb to explode, destroying surrounding ground tiles, creatures, and structures.
     *
     * @param groundArray the array of ground tiles
     * @param tileSize the size of the tiles
     * @param creatures the list of creatures
     * @param structures the list of structures
     * @param gamePanel the game panel
     */
    public void explode(Ground[][] groundArray, int tileSize, List<Creature> creatures, List<Structure> structures, GamePanel gamePanel) {
        this.setDead();
        int col = x / tileSize;//searching for coordinates within 2D array ground
        int row = y / tileSize;//searching for coordinates within 2D array ground

        // Destroy surrounding ground tiles
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newCol = col + i;
                int newRow = row + j;
                if (newCol >= 0 && newCol < groundArray.length && newRow >= 0 && newRow < groundArray[0].length) {
                    if (groundArray[newCol][newRow].isAlive) { // Check if the ground tile is alive
                        groundArray[newCol][newRow].setDead();
                        gamePanel.incrementDestroyedCounter(); // Increment the destroyed counter
                    }
                }
            }
        }

        // Destroy creatures within the explosion radius
        for (Creature creature : creatures) {
            if (isWithinExplosionRadius(creature.getX(), creature.getY(), tileSize)) {
                creature.setDead();
            }
        }
        // Destroy structures within the explosion radius
        for (Structure structure : structures) {
            if (isWithinExplosionRadius(structure.getX(), structure.getY(), tileSize)) {
                structure.setDead();
            }
        }
    }

    /**
     * Checks if an entity is within the explosion radius of the bomb.
     *
     * @param entityX the x-coordinate of the entity
     * @param entityY the y-coordinate of the entity
     * @param tileSize the size of the tiles
     * @return true if the entity is within the explosion radius, false otherwise
     */
    private boolean isWithinExplosionRadius(int entityX, int entityY, int tileSize) {
        int col = x / tileSize;
        int row = y / tileSize;
        int entityCol = entityX / tileSize;
        int entityRow = entityY / tileSize;

        return Math.abs(col - entityCol) <= 1 && Math.abs(row - entityRow) <= 1;
    }

    /**
     * Creates a random bomb within the specified constraints.
     *
     * @param maxScreenCol the maximum screen columns
     * @param maxScreenRow the maximum screen rows
     * @param tileSize     the size of the tiles
     * @param color        the color of the bomb
     * @return a new Bomb instance
     */
    public static Bomb createRandomBomb(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Bomb(randomX, randomY, tileSize, color);
    }
}
