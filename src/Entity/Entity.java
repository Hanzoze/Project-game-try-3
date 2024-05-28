package Entity;

import java.awt.*;

public abstract class Entity {
    public int x, y; // координаты
    public int size; // размер
    public boolean isAlive; // жив или мертв
    public Color color;

    public Entity(int x, int y, int size, Color color){
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        this.isAlive=true;
    }
    public void draw(Graphics g) {
        if (isAlive) {
            g.setColor(color);
            g.fillRect(x, y, size, size);
        }
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

    // Установить новые координаты воина
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
}
