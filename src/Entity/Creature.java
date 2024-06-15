package Entity;

import java.awt.*;
import java.util.Random;

public class Creature extends Entity {
    //constructor for creature
    public Creature(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    //searching for new position
    private static Point calculateNewPosition(int currentX, int currentY, int tileSize) {
        Random random = new Random();
        int dx = random.nextInt(3) - 1; // -1, 0 or 1
        int dy = random.nextInt(3) - 1; // -1, 0 or 1
        int newX = currentX + dx * tileSize; //correct scaling on the map
        int newY = currentY + dy * tileSize; //correct scaling on the map
        return new Point(newX, newY);
    }

    //method for moving agents on the map
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
