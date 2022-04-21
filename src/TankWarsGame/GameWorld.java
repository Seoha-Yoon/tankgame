package TankWarsGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWorld extends JPanel {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 768;
    public static final int WORLD_WIDTH = 2000;
    public static final int WORLD_HEIGHT = 2000;
    private BufferedImage player1Won;
    private BufferedImage player1Lost;
    private BufferedImage player2Won;
    private BufferedImage player2Lost;
    private JFrame jFrame;
    private BufferedImage world;
    public static BufferedImage bulletImg;
    public static BufferedImage damageUpBullet;
    private Graphics2D buffer;
    ArrayList<GameObject> gameObjects;
    private Tank player1;
    private Tank player2;
    ArrayList<Bullet> p1Bullet;
    ArrayList<Bullet> p2Bullet;
    static long tickCount;

    public GameWorld() {
    }

    public static void main(String[] argv) {
        GameWorld demo = new GameWorld();
        demo.init();

        try {
            while (true) {
                demo.gameObjects.forEach((gameObject) -> {
                    gameObject.update();
                });
                demo.collisionDetection();
                demo.repaint();
                ++tickCount;
                Thread.sleep(6L);
            }
        } catch (InterruptedException var3) {
            System.out.println(var3);
        }
    }

    public void init() {
        this.jFrame = new JFrame("Tank Game");
        this.world = new BufferedImage(2000, 2000, 1);
        BufferedImage tankImage1 = null;
        BufferedImage tankImage2 = null;
        BufferedImage breakWall = null;
        BufferedImage unBreakWall = null;
        BufferedImage background = null;
        BufferedImage DamageUp = null;
        BufferedImage getLife = null;
        this.gameObjects = new ArrayList();

        try {
            background = ImageIO.read(GameWorld.class.getClassLoader().getResource("Background.png"));
            tankImage1 = ImageIO.read(GameWorld.class.getClassLoader().getResource("tank1.png"));
            tankImage2 = ImageIO.read(GameWorld.class.getClassLoader().getResource("tank2.png"));
            breakWall = ImageIO.read(GameWorld.class.getClassLoader().getResource("breakWall.gif"));
            unBreakWall = ImageIO.read(GameWorld.class.getClassLoader().getResource("unbreakWall.gif"));
            bulletImg = ImageIO.read(GameWorld.class.getClassLoader().getResource("bullet.png"));
            DamageUp = ImageIO.read(GameWorld.class.getClassLoader().getResource("missile.png"));
            getLife = ImageIO.read(GameWorld.class.getClassLoader().getResource("heart.png"));
            damageUpBullet = ImageIO.read(GameWorld.class.getClassLoader().getResource("missile.png"));
            this.player1Lost = ImageIO.read(GameWorld.class.getClassLoader().getResource("player1Lost.png"));
            this.player1Won = ImageIO.read(GameWorld.class.getClassLoader().getResource("player1Won.png"));
            this.player2Lost = ImageIO.read(GameWorld.class.getClassLoader().getResource("player2Lost.png"));
            this.player2Won = ImageIO.read(GameWorld.class.getClassLoader().getResource("player2Won.png"));

            for (int i = 0; i < 2000; i += 320) {
                for (int j = 0; j < 2000; j += 240) {
                    this.gameObjects.add(new BackGround(i, j, background));
                }
            }

            InputStreamReader isr = new InputStreamReader(GameWorld.class.getClassLoader().getResourceAsStream("map/map1"));
            BufferedReader mapReader = new BufferedReader(isr);
            String row = mapReader.readLine();
            if (row == null) {
                throw new IOException("no data in file");
            }

            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for (int curRow = 0; curRow < numRows; ++curRow) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");

                for (int curCol = 0; curCol < numCols; ++curCol) {
                    String var16 = mapInfo[curCol];
                    byte var17 = -1;
                    switch (var16.hashCode()) {
                        case 50:
                            if (var16.equals("2")) {
                                var17 = 0;
                            }
                            break;
                        case 51:
                            if (var16.equals("3")) {
                                var17 = 1;
                            }
                            break;
                        case 52:
                            if (var16.equals("4")) {
                                var17 = 3;
                            }
                            break;
                        case 53:
                            if (var16.equals("5")) {
                                var17 = 4;
                            }
                        case 54:
                        case 55:
                        case 56:
                        default:
                            break;
                        case 57:
                            if (var16.equals("9")) {
                                var17 = 2;
                            }
                    }

                    switch (var17) {
                        case 0:
                            this.gameObjects.add(new BreakableWall(curCol * 30, curRow * 30, breakWall));
                            break;
                        case 1:
                        case 2:
                            this.gameObjects.add(new UnbreakableWall(curCol * 30, curRow * 30, unBreakWall));
                            break;
                        case 3:
                            this.gameObjects.add(new GameComponent(curCol * 30, curRow * 30, DamageUp, 4));
                            break;
                        case 4:
                            this.gameObjects.add(new GameComponent(curCol * 30, curRow * 30, getLife, 5));
                    }
                }
            }
        } catch (IOException var18) {
            System.out.println(var18.getMessage());
        }

        this.player1 = new Tank(100, 100, 0, 0, 0, tankImage1);
        this.player2 = new Tank(1700, 1800, 0, 0, 180, tankImage2);
        this.p1Bullet = this.player1.getAmmo();
        this.p2Bullet = this.player2.getAmmo();
        TankControl tankOneControl = new TankControl(this.player1, 38, 40, 37, 39, 10);
        TankControl tankTwoControl = new TankControl(this.player2, 87, 83, 65, 68, 32);
        this.gameObjects.add(this.player1);
        this.gameObjects.add(this.player2);
        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(tankOneControl);
        this.jFrame.addKeyListener(tankTwoControl);
        this.jFrame.setSize(1280, 798);
        this.jFrame.setResizable(false);
        this.jFrame.setLocationRelativeTo((Component) null);
        this.jFrame.setDefaultCloseOperation(3);
        this.jFrame.setVisible(true);
    }

    private void collisionDetection() {
        for (int i = 0; i < this.gameObjects.size(); ++i) {
            if (this.player1.getHitBox().intersects(((GameObject) this.gameObjects.get(i)).getHitBox())) {
                this.player1.collision((GameObject) this.gameObjects.get(i));
                if (((GameObject) this.gameObjects.get(i)).getState() == 5 || ((GameObject) this.gameObjects.get(i)).getState() == 4) {
                    this.gameObjects.remove(i);
                }
            }

            if (this.player2.getHitBox().intersects(((GameObject) this.gameObjects.get(i)).getHitBox())) {
                this.player2.collision((GameObject) this.gameObjects.get(i));
                if (((GameObject) this.gameObjects.get(i)).getState() == 5 || ((GameObject) this.gameObjects.get(i)).getState() == 4) {
                    this.gameObjects.remove(i);
                }
            }

            int j;
            for (j = 0; j < this.p1Bullet.size(); ++j) {
                if (((Bullet) this.p1Bullet.get(j)).getHitBox().intersects(((GameObject) this.gameObjects.get(i)).getHitBox())) {
                    if (((GameObject) this.gameObjects.get(i)).getState() == 2) {
                        ((GameObject) this.gameObjects.get(i)).explode();
                    } else if (((GameObject) this.gameObjects.get(i)).equals(this.player2)) {
                        this.player2.shot((Bullet) this.p1Bullet.get(j));
                    }

                    if (!((GameObject) this.gameObjects.get(i)).equals(this.player1) && !(this.gameObjects.get(i) instanceof BackGround)) {
                        this.p1Bullet.remove(j);
                    }
                }
            }

            for (j = 0; j < this.p2Bullet.size(); ++j) {
                if (((Bullet) this.p2Bullet.get(j)).getHitBox().intersects(((GameObject) this.gameObjects.get(i)).getHitBox())) {
                    if (((GameObject) this.gameObjects.get(i)).getState() == 2) {
                        ((GameObject) this.gameObjects.get(i)).explode();
                    } else if (((GameObject) this.gameObjects.get(i)).equals(this.player1)) {
                        this.player1.shot((Bullet) this.p2Bullet.get(j));
                    }

                    if (!((GameObject) this.gameObjects.get(i)).equals(this.player2) && !(this.gameObjects.get(i) instanceof BackGround)) {
                        this.p2Bullet.remove(j);
                    }
                }
            }
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        this.buffer = this.world.createGraphics();
        if (this.player1.getLifeCount() > 0 && this.player2.getLifeCount() > 0) {
            int sub1x;
            for (sub1x = 0; sub1x < this.gameObjects.size(); ++sub1x) {
                ((GameObject) this.gameObjects.get(sub1x)).drawImage(this.buffer);
                if (((GameObject) this.gameObjects.get(sub1x)).getState() == 1) {
                    this.gameObjects.remove(sub1x);
                }
            }

            sub1x = this.player1.getX() - 320;
            if (this.player1.getX() - 320 < 0) {
                sub1x = 0;
            } else if (this.player1.getX() + 320 > 2000) {
                sub1x = 1360;
            }

            int sub1y = this.player1.getY() + 45 - 384;
            if (this.player1.getY() + 45 - 384 < 0) {
                sub1y = 0;
            } else if (this.player1.getY() + 45 + 384 > 2000) {
                sub1y = 1232;
            }

            int sub2x = this.player2.getX() - 320;
            if (this.player2.getX() - 320 < 0) {
                sub2x = 0;
            } else if (this.player2.getX() + 160 + 320 > 2000) {
                sub2x = 1360;
            }

            int sub2y = this.player2.getY() + 45 - 384;
            if (this.player2.getY() + 45 - 384 < 0) {
                sub2y = 0;
            } else if (this.player2.getY() + 45 + 384 > 2000) {
                sub2y = 1232;
            }

            BufferedImage leftHalf = this.world.getSubimage(sub1x, sub1y, 640, 768);
            BufferedImage rightHalf = this.world.getSubimage(sub2x, sub2y, 640, 768);
            BufferedImage miniMap = this.world.getSubimage(0, 0, 2000, 2000);
            g2.drawImage(leftHalf, 0, 0, (ImageObserver) null);
            g2.drawImage(rightHalf, 644, 0, (ImageObserver) null);
            g2.scale(0.1D, 0.1D);
            g2.drawImage(miniMap, 5760, 200, (ImageObserver) null);
        } else if (this.player1.getLifeCount() > 0) {
            g2.drawImage(this.player1Won, 0, 0, (ImageObserver) null);
            g2.drawImage(this.player2Lost, 644, 0, (ImageObserver) null);
        } else {
            g2.drawImage(this.player1Lost, 0, 0, (ImageObserver) null);
            g2.drawImage(this.player2Won, 644, 0, (ImageObserver) null);
        }

    }
}