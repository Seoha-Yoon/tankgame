package TankWarsGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected int angle;
    protected int state;
    protected BufferedImage img;
    Rectangle hitBox;

    public int getState() {
        return this.state;
    }

    public GameObject() {
    }

    public abstract void update();

    public abstract void collision(GameObject var1);

    public abstract void explode();

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVx() {
        return this.vx;
    }

    public int getVy() {
        return this.vy;
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.img, this.x, this.y, (ImageObserver)null);
    }
}