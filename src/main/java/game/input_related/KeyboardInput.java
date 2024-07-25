package game.input_related;

import game.window_related.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;

    private double amount = 5;

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
                gamePanel.movePlayer(0, -amount);
                break;
            case KeyEvent.VK_A:
                gamePanel.movePlayer(-amount, 0);
                break;
            case KeyEvent.VK_S:
                gamePanel.movePlayer(0, amount);
                break;
            case KeyEvent.VK_D:
                gamePanel.movePlayer(amount, 0);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D:
                gamePanel.movePlayer(0, 0);
                break;
        }

    }

}
