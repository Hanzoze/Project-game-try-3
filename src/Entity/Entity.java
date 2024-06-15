package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity {
    public int x, y; // coordinates
    public int size; // size
    public boolean isAlive; // dead or alive
    public Color color; //color
    public BufferedImage builder_image, warrior_red_image, warrior_blue_image, bomb_image; //images

    //constructor for all other classes that were created from Entity
    public Entity(int x, int y, int size, Color color){
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        this.isAlive=true;
        getImage();
    }

    //set status dead or alive
    public void setDead() {
        isAlive = false;
    }
    public void setAlive(){
        isAlive = true;
    }

    //check if alive
    public boolean isAlive() {
        return isAlive;
    }

    // get new coordinates
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    // Set new coordinates
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    //checking if on the same tile
    public boolean isCollidingWith(Entity other) {
        return this != other && this.getX() == other.getX() && this.getY() == other.getY();
    }

    //writing path to every image
    public void getImage(){
        try{
            builder_image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/images/builder.png")));
            warrior_red_image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/images/warrior_red.png")));
            warrior_blue_image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/images/warrior_blue.png")));
            bomb_image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Main/images/bomb.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //drawing correct image for every agent
    public void draw(Graphics2D g) {
        if (isAlive) {
            if (this instanceof Warrior) {
                g.drawImage(color.equals(Color.RED) ? warrior_red_image : warrior_blue_image, x, y, size, size, null);
            } else if (this instanceof Builder) {
                g.drawImage(builder_image, x, y, size, size, null);
            } else if (this instanceof Bomb) {
                g.drawImage(bomb_image, x, y, size, size, null);
            } else {
                g.setColor(color);
                g.fillRect(x, y, size, size); // Fallback to color if no image is found
            }
        }
    }
}
