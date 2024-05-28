package Entity;

import java.awt.*;

public class Ground extends Structure {
    public Ground(int x, int y, int size, Color color) {
        super(x, y, size, color);
        this.isAlive = true; // По умолчанию клетка не сломана
    }
    public static Ground[][] creatnewground(int maxScreenCol, int maxScreenRow, int tileSize, Color color){
        Ground[][] groundArray = new Ground[maxScreenCol][maxScreenRow];
        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
                groundArray[x][y] = new Ground(x * tileSize, y * tileSize, tileSize, color);
            }
        }
        return groundArray;
    }
    public void drawBorder(Graphics g) {
        g.setColor(Color.BLACK); // Change color as needed
        g.drawRect(x, y, size, size);
    }
}
