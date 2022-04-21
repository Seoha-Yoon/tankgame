package TankWarsGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class BackGround extends GameObject {
    public BackGround(int x, int y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.state = 9;
        this.hitBox = new Rectangle(this.x, this.y, this.img.getWidth(), this.img.getHeight());
    }

    public void update() {
    }

    public void collision(GameObject obj) {
    }

    public void explode() {
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.img, this.x, this.y, (ImageObserver)null);
    }
}