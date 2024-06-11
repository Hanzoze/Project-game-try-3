package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity {
    public int x, y; // координаты
    public int size; // размер
    public boolean isAlive; // жив или мертв
    public Color color;
    public BufferedImage builder_image, warrior_red_image, warrior_blue_image, bomb_image;


    public Entity(int x, int y, int size, Color color){
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        this.isAlive=true;
        getImage();
    }

    public void setDead() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

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

    public void setAlive(){
        isAlive = true;
    }

    public boolean isCollidingWith(Entity other) {
        return this != other && this.getX() == other.getX() && this.getY() == other.getY();
    }

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
