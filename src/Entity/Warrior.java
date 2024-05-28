package Entity;

import Main.GamePanel;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Warrior extends Creature {
    public Warrior(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    public static Warrior createRandomWarrior(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Warrior(randomX, randomY, tileSize, color);
    }

    public void handleEncounter(List<Creature> creatures, Ground[][] groundArray, int tileSize, GamePanel gamePanel) {
        for (Creature creature : creatures) {
            if (this != creature && getX() == creature.getX() && getY() == creature.getY() && creature.isAlive()) {
                if (creature instanceof Warrior) {
                    // Encounter with another Warrior
                    int col = getX() / tileSize;
                    int row = getY() / tileSize;
                    if (groundArray[col][row].isAlive()) {
                        gamePanel.incrementDestroyedCounter();
                        groundArray[col][row].setDead();
                    }
                    for (Creature nonWarrior: creatures){
                        if(!(nonWarrior instanceof Warrior) && nonWarrior.isAlive && nonWarrior.getX()==creature.getX() && nonWarrior.getY()==creature.getY()){
                            nonWarrior.setDead();
                            gamePanel.incrementCounter();
                        }
                    }
                }
            }
        }
    }
}
