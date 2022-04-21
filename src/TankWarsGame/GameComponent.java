package TankWarsGame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GameComponent extends GameObject {
    public GameComponent(int x, int y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.hitBox = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public GameComponent(int x, int y, BufferedImage img, int state) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.state = state;
        this.hitBox = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public void update() {
    }

    public void collision(GameObject obj) {
    }

    public void explode() {
    }
}