package Entity;

import java.awt.*;

/**
 * Represents the ground in the game.
 */
public class Ground extends Structure {
    /**
     * Constructor for Ground.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size the size of the ground tile
     * @param color the color of the ground tile
     */
    public Ground(int x, int y, int size, Color color) {
        super(x, y, size, color);
        this.isAlive = true; // По умолчанию клетка не сломана
    }

    /**
     * Creates a new ground array covering the whole game panel.
     *
     * @param maxScreenCol the maximum screen columns
     * @param maxScreenRow the maximum screen rows
     * @param tileSize the size of the tiles
     * @param color the color of the ground tiles
     * @return a 2D array of Ground instances
     */
    public static Ground[][] creatnewground(int maxScreenCol, int maxScreenRow, int tileSize, Color color){
        Ground[][] groundArray = new Ground[maxScreenCol][maxScreenRow];
        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
                groundArray[x][y] = new Ground(x * tileSize, y * tileSize, tileSize, color);
            }
        }
        return groundArray;
    }

    /**
     * Draws the border for the ground tile.
     *
     * @param g the graphics context
     */
    public void drawBorder(Graphics g) {
        g.setColor(Color.BLACK); // Change color as needed
        g.drawRect(x, y, size, size);
    }
}
