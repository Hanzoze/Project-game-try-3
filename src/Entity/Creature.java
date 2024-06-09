package Entity;

import java.awt.*;
import java.util.Random;

public class Creature extends Entity {
    public Creature(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }
    private static Point calculateNewPosition(int currentX, int currentY, int tileSize) {
        Random random = new Random();
        int dx = random.nextInt(3) - 1; // -1, 0 or 1
        int dy = random.nextInt(3) - 1; // -1, 0 or 1
        int newX = currentX + dx * tileSize;
        int newY = currentY + dy * tileSize;
        return new Point(newX, newY);
    }
    public static void move(Creature creature, int tileSize, int screenWidth, int screenHeight) {
        Point NewPosition = Creature.calculateNewPosition(creature.getX(), creature.getY(), tileSize);
        int NewX = (int) NewPosition.getX();
        int NewY = (int) NewPosition.getY();
        if (NewX >= 0 && NewX < screenWidth && NewY >= 0 && NewY < screenHeight) {
            creature.setX(NewX);
            creature.setY(NewY);
        }
    }
}
