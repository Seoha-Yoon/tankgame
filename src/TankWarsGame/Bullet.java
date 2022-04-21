package TankWarsGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Bullet extends GameObject {
    private Tank shotBy;
    private BufferedImage explosionImg;
    private int explosionTime;
    private int damage;
    private boolean collided;
    int R = 7;

    public Bullet(int x, int y, int angle, BufferedImage img, Tank t) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.img = img;
        this.shotBy = t;
        this.collided = false;
        this.damage = 5;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
        this.state = 7;
    }

    public void moveForward() {
        this.vx = (int)Math.round((double)this.R * Math.cos(Math.toRadians((double)this.angle)));
        this.vy = (int)Math.round((double)this.R * Math.sin(Math.toRadians((double)this.angle)));
        this.x += this.vx;
        this.y += this.vy;
        this.checkBorder();
        this.hitBox.setLocation(this.x, this.y);
    }

    public void checkBorder() {
        if (this.x < 30) {
            this.x = 30;
        }

        if (this.x >= 1912) {
            this.x = 1912;
        }

        if (this.y < 40) {
            this.y = 40;
        }

        if (this.y >= 1920) {
            this.y = 1920;
        }

    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Tank getTank() {
        return this.shotBy;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void update() {
        this.moveForward();
    }

    public void collision(GameObject obj) {
    }

    public void explode() {
    }

    public int getDamage() {
        return this.damage;
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance((double)this.x, (double)this.y);
        rotation.rotate(Math.toRadians((double)this.angle), (double)this.img.getWidth() / 2.0D, (double)this.img.getHeight() / 2.0D);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);
        g2d.drawImage(this.img, rotation, (ImageObserver)null);
    }
}