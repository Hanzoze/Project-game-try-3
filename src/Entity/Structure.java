package Entity;

import java.awt.*;
/**
 * Represents a structure in the game.
 */
public class Structure extends Entity {
    /**
     * Constructor for Structure.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size the size of the structure
     * @param color the color of the structure
     */
    public Structure(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }
}
