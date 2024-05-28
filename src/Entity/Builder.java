package Entity;

import Main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Builder extends Creature {
    public Builder(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    public static Builder createRandomBuilder(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Builder(randomX, randomY, tileSize, color);
    }

    public void repairGroundIfBroken(Ground[][] groundArray, int tileSize, GamePanel gamePanel) {
        int col = getX() / tileSize;
        int row = getY() / tileSize;
        if (col >= 0 && col < groundArray.length && row >= 0 && row < groundArray[0].length) {
            Ground ground = groundArray[col][row];
            if (!ground.isAlive()) {
                ground.setAlive();
                gamePanel.incrementRepairedCounter();
            }
        }
    }
}
