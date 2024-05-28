package Entity;

import Main.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Bomb extends Structure {
    public Bomb(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    // Method to handle collision
    public void handleCollision(List<Creature> creatures, List<Structure> structures, Ground[][] groundArray, int tileSize, GamePanel gamePanel) {
        for (Creature creature : creatures) {
            if (isColliding(creature.getX(), creature.getY(), tileSize)) {
                explode(groundArray, tileSize, creatures, structures, gamePanel);
                break;
            }
        }
        for (Structure structure : structures) {
            if (this != structure && isColliding(structure.getX(), structure.getY(), tileSize)) {
                explode(groundArray, tileSize, creatures, structures, gamePanel);
                break;
            }
        }
    }

    // Method for explosion
    public void explode(Ground[][] groundArray, int tileSize, List<Creature> creatures, List<Structure> structures, GamePanel gamePanel) {
        this.setDead();
        int col = x / tileSize;
        int row = y / tileSize;

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

    // Collision check
    private boolean isColliding(int entityX, int entityY, int tileSize) {
        return this.getX() == entityX && this.getY() == entityY;
    }

    private boolean isWithinExplosionRadius(int entityX, int entityY, int tileSize) {
        int col = x / tileSize;
        int row = y / tileSize;
        int entityCol = entityX / tileSize;
        int entityRow = entityY / tileSize;

        return Math.abs(col - entityCol) <= 1 && Math.abs(row - entityRow) <= 1;
    }

    public static Bomb createRandomBomb(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Bomb(randomX, randomY, tileSize, color);
    }
}
