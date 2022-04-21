package TankWarsGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Tank extends GameObject {
    private int lifeCount;
    private int health;
    private int bulletDamage;
    private int fire;
    private BufferedImage lifeImg;
    private BufferedImage damageUpBullet;
    private BufferedImage[] healthBars;
    private GameObject healthBar;
    private int angle;
    private ArrayList<Bullet> ammo;
    private final int R = 2;
    private final int ROTATION_SPEED = 3;
    private int OriginX;
    private int OriginY;
    private boolean powerUp;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    public boolean isUpPressed() {
        return this.UpPressed;
    }

    public boolean isDownPressed() {
        return this.DownPressed;
    }

    public Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vx = vy;
        this.angle = angle;
        this.OriginX = x;
        this.OriginY = y;
        this.img = img;
        this.ammo = new ArrayList();
        this.health = 100;
        this.lifeCount = 3;
        this.state = 10;
        this.healthBars = new BufferedImage[10];
        this.powerUp = false;
        this.bulletDamage = 10;
        this.hitBox = new Rectangle(x, y, img.getWidth(), img.getHeight());

        try {
            this.healthBars[0] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health1.png"));
            this.healthBars[1] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health2.png"));
            this.healthBars[2] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health3.png"));
            this.healthBars[3] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health4.png"));
            this.healthBars[4] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health5.png"));
            this.healthBars[5] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health6.png"));
            this.healthBars[6] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health7.png"));
            this.healthBars[7] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health8.png"));
            this.healthBars[8] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health9.png"));
            this.healthBars[9] = ImageIO.read(GameWorld.class.getClassLoader().getResource("health10.png"));
            this.lifeImg = ImageIO.read(GameWorld.class.getClassLoader().getResource("icon.png"));
            this.damageUpBullet = ImageIO.read(GameWorld.class.getClassLoader().getResource("missile.png"));
        } catch (IOException var8) {
            System.out.println(var8 + " No resource found");
        }

    }

    public Rectangle getHitBox() {
        return this.hitBox.getBounds();
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {
        this.ShootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    public int getHealth() {
        return this.health;
    }

    public int getLifeCount() {
        return this.lifeCount;
    }

    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }

        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }

        if (this.RightPressed) {
            this.rotateRight();
        }

        if (this.ShootPressed && GameWorld.tickCount % 20L == 0L) {
            Bullet b;
            if (this.powerUp) {
                b = new Bullet(this.x + 20, this.y + 10, this.angle, this.damageUpBullet, this);
                this.ammo.add(b);
            } else {
                b = new Bullet(this.x + 20, this.y + 20, this.angle, GameWorld.bulletImg, this);
                this.ammo.add(b);
            }
        }

        this.ammo.forEach((bullet) -> {
            bullet.update();
        });
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance((double)this.x, (double)this.y);
        rotation.rotate(Math.toRadians((double)this.angle), (double)this.img.getWidth() / 2.0D, (double)this.img.getHeight() / 2.0D);
        Graphics2D g2d = (Graphics2D)g;
        this.ammo.forEach((bullet) -> {
            bullet.drawImage(g);
        });
        g2d.drawImage(this.img, rotation, (ImageObserver)null);
        if (this.health > 90) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[9]);
            this.healthBar.drawImage(g);
        } else if (80 < this.health && this.health <= 90) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[8]);
            this.healthBar.drawImage(g);
        } else if (70 < this.health && this.health <= 80) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[7]);
            this.healthBar.drawImage(g);
        } else if (60 < this.health && this.health <= 70) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[6]);
            this.healthBar.drawImage(g);
        } else if (50 < this.health && this.health <= 60) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[5]);
            this.healthBar.drawImage(g);
        } else if (40 < this.health && this.health <= 50) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[4]);
            this.healthBar.drawImage(g);
        } else if (30 < this.health && this.health <= 40) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[3]);
            this.healthBar.drawImage(g);
        } else if (20 < this.health && this.health <= 30) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[2]);
            this.healthBar.drawImage(g);
        } else if (10 < this.health && this.health <= 20) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[1]);
            this.healthBar.drawImage(g);
        } else if (this.health <= 10) {
            this.healthBar = new GameComponent(this.x, this.y + 50, this.healthBars[0]);
            this.healthBar.drawImage(g);
        }

        for(int i = 0; i < this.lifeCount; ++i) {
            GameObject lifeobj = new GameComponent(this.x + i * this.lifeImg.getWidth(), this.y + 30 + this.lifeImg.getHeight(), this.lifeImg);
            lifeobj.drawImage(g);
        }

    }

    private void moveForwards() {
        this.vx = (int)Math.round(2.0D * Math.cos(Math.toRadians((double)this.angle)));
        this.vy = (int)Math.round(2.0D * Math.sin(Math.toRadians((double)this.angle)));
        this.x += this.vx;
        this.y += this.vy;
        this.checkBorder();
        this.hitBox.setLocation(this.x, this.y);
    }

    private void moveBackwards() {
        this.vx = (int)Math.round(2.0D * Math.cos(Math.toRadians((double)this.angle)));
        this.vy = (int)Math.round(2.0D * Math.sin(Math.toRadians((double)this.angle)));
        this.x -= this.vx;
        this.y -= this.vy;
        this.checkBorder();
        this.hitBox.setLocation(this.x, this.y);
    }

    private void rotateRight() {
        int var10001 = this.angle;
        Objects.requireNonNull(this);
        this.angle = var10001 + 3;
    }

    private void rotateLeft() {
        int var10001 = this.angle;
        Objects.requireNonNull(this);
        this.angle = var10001 - 3;
    }

    private void checkBorder() {
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

    public void collision(GameObject obj) {
        if (obj.getState() < 4) {
            if (this.isDownPressed()) {
                this.x += this.vx;
                this.y += this.vy;
            }

            if (this.isUpPressed()) {
                this.x -= this.vx;
                this.y -= this.vy;
            }
        } else if (obj.getState() == 4) {
            this.powerUp = true;
            this.bulletDamage = 15;
        } else if (obj.getState() == 5) {
            ++this.lifeCount;
        }

        this.hitBox.setLocation(this.x, this.y);
    }

    public void explode() {
    }

    public void shot(Bullet b) {
        this.health -= b.getTank().bulletDamage;
        if (this.lifeCount != 0 && this.health < 0) {
            --this.lifeCount;
            this.health = 100;
            this.x = this.OriginX;
            this.y = this.OriginY;
            this.bulletDamage = 10;
        }

        this.hitBox.setLocation(this.x, this.y);
    }

    public ArrayList<Bullet> getAmmo() {
        return this.ammo;
    }

    public String toString() {
        return "x=" + this.x + ", y=" + this.y + ", angle=" + this.angle;
    }
}