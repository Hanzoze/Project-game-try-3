package Entity;

import Main.GamePanel;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Warrior extends Creature {
    //constructor for Warrior
    public Warrior(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    //method for creating new Warrior in a random place
    public static Warrior createRandomWarrior(int maxScreenCol, int maxScreenRow, int tileSize, Color color) {
        Random random = new Random();
        int randomX = random.nextInt(maxScreenCol) * tileSize;
        int randomY = random.nextInt(maxScreenRow) * tileSize;
        return new Warrior(randomX, randomY, tileSize, color);
    }

    //Warrior encounter
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
