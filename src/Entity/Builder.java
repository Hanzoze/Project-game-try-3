package Entity;

import Main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Builder extends Creature {
    //constructor for Builder
    public Builder(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    //method for creating new Builder in a random place
    public static Builder createRandomBuilder(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Builder(randomX, randomY, tileSize, color);
    }

    //method for Builder encounter
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
