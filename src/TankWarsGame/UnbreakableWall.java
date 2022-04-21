package TankWarsGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class UnbreakableWall extends GameObject {
    public UnbreakableWall(int x, int y, BufferedImage wallImage) {
        this.x = x;
        this.y = y;
        this.state = 3;
        this.img = wallImage;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }

    public void update() {
        this.x += this.vx;
        this.y += this.vy;
        this.hitBox.setLocation(this.x, this.y);
    }

    public void collision(GameObject obj) {
    }

    public void explode() {
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.img, this.x, this.y, (ImageObserver)null);
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }
}