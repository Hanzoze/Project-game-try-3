package Entity;

import java.awt.*;
import java.util.Random;
/**
 * Represents a creature in the game.
 */
public class Creature extends Entity {

    /**
     * Constructor for Creature.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size the size of the creature
     * @param color the color of the creature
     */
    public Creature(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    /**
     * Calculates a new position for the creature.
     *
     * @param currentX the current x-coordinate
     * @param currentY the current y-coordinate
     * @param tileSize the size of the tiles
     * @return the new position as a Point
     */
    private static Point calculateNewPosition(int currentX, int currentY, int tileSize) {
        Random random = new Random();
        int dx = random.nextInt(3) - 1; // -1, 0 or 1
        int dy = random.nextInt(3) - 1; // -1, 0 or 1
        int newX = currentX + dx * tileSize; //correct scaling on the map
        int newY = currentY + dy * tileSize; //correct scaling on the map
        return new Point(newX, newY);
    }

    /**
     * Moves the creature to a new position on the map.
     *
     * @param creature the creature to move
     * @param tileSize the size of the tiles
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public static void move(Creature creature, int tileSize, int screenWidth, int screenHeight) {
        Point NewPosition = Creature.calculateNewPosition(creature.getX(), creature.getY(), tileSize);
        int NewX = (int) NewPosition.getX();
        int NewY = (int) NewPosition.getY();
        if (NewX >= 0 && NewX < screenWidth && NewY >= 0 && NewY < screenHeight) { //checking if new position is on the map
            creature.setX(NewX); //moving agent
            creature.setY(NewY); //moving agent
        }
    }
}
