package TankWarsGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BreakableWall extends GameObject {
    int x;
    int y;
    BufferedImage[] explosion;

    public BreakableWall(int x, int y, BufferedImage wallImage) {
        this.x = x;
        this.y = y;
        this.state = 2;
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
        this.state = 1;

        try {
            this.img = ImageIO.read(GameWorld.class.getClassLoader().getResource("Explosion_small.gif"));
        } catch (IOException var2) {
            System.out.println(var2);
        }

    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(this.img, this.x, this.y, (ImageObserver)null);
    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }
}