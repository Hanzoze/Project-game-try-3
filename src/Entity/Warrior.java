package Entity;

import Main.GamePanel;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Represents a warrior in the game.
 */
public class Warrior extends Creature {
    /**
     * Constructor for Warrior.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size the size of the warrior
     * @param color the color of the warrior
     */
    public Warrior(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    /**
     * Creates a random warrior within the specified constraints.
     *
     * @param maxScreenCol the maximum screen columns
     * @param maxScreenRow the maximum screen rows
     * @param tileSize the size of the tiles
     * @param color the color of the warrior
     * @return a new Warrior instance
     */
    public static Warrior createRandomWarrior(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Warrior(randomX, randomY, tileSize, color);
    }

    /**
     * Handles encounters between the warrior and other creatures.
     *
     * @param creatures the list of creatures
     * @param groundArray the array of ground tiles
     * @param tileSize the size of the tiles
     * @param gamePanel the game panel
     */
    public void handleEncounter(List<Creature> creatures, Ground[][] groundArray, int tileSize, GamePanel gamePanel) {
        for (Creature creature : creatures) {
            if (this.isCollidingWith(creature) && creature.isAlive() && creature instanceof Warrior && !this.color.equals(creature.color)) { //checking if it is Warrior on the same tile but different color
                int col = getX() / tileSize; //searching for coordinates within 2D array ground
                int row = getY() / tileSize; //searching for coordinates within 2D array ground
                if (groundArray[col][row].isAlive()) { //destroying ground where 2 different Warriors set
                    gamePanel.incrementDestroyedCounter();
                    groundArray[col][row].setDead();
                }
                for (Creature nonWarrior: creatures){ //killing all creature who are not Warrior on the same tile where 2 Warriors are set
                    if(!(nonWarrior instanceof Warrior) && nonWarrior.isAlive && nonWarrior.isCollidingWith(creature)){
                        nonWarrior.setDead();
                        gamePanel.incrementCounter();
                    }
                }
            }
        }
    }
}
