package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
/**
 * Represents a general entity in the game.
 */
public abstract class Entity {
    public int x, y; // coordinates
    public int size; // size
    public boolean isAlive; // dead or alive
    public Color color; //color
    public BufferedImage builder_image, warrior_red_image, warrior_blue_image, bomb_image; //images

    /**
     * Constructor for all other classes that are created from Entity.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param size the size of the entity
     * @param color the color of the entity
     */
    public Entity(int x, int y, int size, Color color){
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        this.isAlive=true;
        getImage();
    }

    /**
     * Sets the status of the entity to dead.
     */
    public void setDead() {
        isAlive = false;
    }

    /**
     * Sets the status of the entity to alive.
     */
    public void setAlive(){
        isAlive = true;
    }

    /**
     * Checks if the entity is alive.
     *
     * @return true if the entity is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Gets the x-coordinate of the entity.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the entity.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the entity.
     *
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the entity.
     *
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Checks if this entity is colliding with another entity.
     *
     * @param other the other entity
     * @return true if the entities are colliding, false otherwise
     */
    public boolean isCollidingWith(Entity other) {
        return this != other && this.getX() == other.getX() && this.getY() == other.getY();
    }

    /**
     * Loads images for different types of entities.
     */
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

    /**
     * Draws the correct image for each entity.
     *
     * @param g the graphics context
     */
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
