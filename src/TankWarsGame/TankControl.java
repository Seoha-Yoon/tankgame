package TankWarsGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankControl implements KeyListener {
    private Tank tank;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl(Tank tank, int up, int down, int left, int right, int shoot) {
        this.tank = tank;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        if (keyPressed == this.up) {
            this.tank.toggleUpPressed();
        }

        if (keyPressed == this.down) {
            this.tank.toggleDownPressed();
        }

        if (keyPressed == this.left) {
            this.tank.toggleLeftPressed();
        }

        if (keyPressed == this.right) {
            this.tank.toggleRightPressed();
        }

        if (keyPressed == this.shoot) {
            this.tank.toggleShootPressed();
        }

    }

    public void keyReleased(KeyEvent e) {
        int keyReleased = e.getKeyCode();
        if (keyReleased == this.up) {
            this.tank.unToggleUpPressed();
        }

        if (keyReleased == this.down) {
            this.tank.unToggleDownPressed();
        }

        if (keyReleased == this.left) {
            this.tank.unToggleLeftPressed();
        }

        if (keyReleased == this.right) {
            this.tank.unToggleRightPressed();
        }

        if (keyReleased == this.shoot) {
            this.tank.unToggleShootPressed();
        }

    }
}