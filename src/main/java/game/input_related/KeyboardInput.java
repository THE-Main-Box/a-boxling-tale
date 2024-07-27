package game.input_related;

import game.window_related.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;

    private double walkAcceleration = 100;
    private double jumpForce = 20;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.movePlayer('Y', -walkAcceleration);
                break;
            case KeyEvent.VK_A:
                gamePanel.movePlayer('X', -walkAcceleration);
                break;
            case KeyEvent.VK_S:
                gamePanel.movePlayer('Y', walkAcceleration);
                break;
            case KeyEvent.VK_D:
                gamePanel.movePlayer('X', walkAcceleration);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.movePlayer('Y', jumpForce);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.movePlayer('Y', 0);
                break;
            case KeyEvent.VK_A:
                gamePanel.movePlayer('X', 0);
                break;
            case KeyEvent.VK_S:
                gamePanel.movePlayer('Y', 0);
                break;
            case KeyEvent.VK_D:
                gamePanel.movePlayer('X', 0);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.movePlayer('Y', 0);
                break;
        }

    }

}
